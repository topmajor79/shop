package egovframework.shop.web;

//import org.apache.ibatis.type.TypeReference;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference; 


import egovframework.shop.service.CategoryVO;
import egovframework.shop.service.ProductService;
import egovframework.shop.service.ProductVO;
import egovframework.shop.service.UserVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

//@CrossOrigin(origins = "http://localhost:8080",
//allowCredentials = "true")
@CrossOrigin(
	    origins = {
	        "http://localhost:8080",
	        "http://192.168.0.12:8080"
	    },
	    allowCredentials = "true"
	)

@RestController
@RequestMapping("/api/shop")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Resource(name = "productService")
    private ProductService productService;

    @GetMapping("/productList.do")
    public List<?> selectProductList( 
            @ModelAttribute("productVO") ProductVO productVO,
            ModelMap model) throws Exception {
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(productVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(productVO.getPageUnit());
        paginationInfo.setPageSize(productVO.getPageSize());

        productVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        productVO.setLastIndex(paginationInfo.getLastRecordIndex());
        productVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> productList = productService.selectProductList(productVO);
        model.addAttribute("resultList", productList);

        int totCnt = productService.selectProductListTotCnt(productVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("paginationInfo", paginationInfo);

        return productList;
    }

    @GetMapping(value="/product/{productCode}.do", produces="application/json;charset=UTF-8")
    public ProductVO selectProduct(@PathVariable("productCode") String productCode) throws Exception {
    	logger.info("dddd===="+productCode);
    	ProductVO productVO = new ProductVO();
    	productVO.setProductCode(productCode);
    	
    	ProductVO product = productService.selectProduct(productVO);
        //model.addAttribute("resultList", product);
    	if(product == null) {
    		product = new ProductVO();
    	}
    	
    	List<CategoryVO> category =  productService.selectCategory();//selectCategory
    	product.setCategoryList(category);
    	

        return product;
    }
    
//    private static final String UPLOAD_PATH = "C:/upload/";
//
//    @PostMapping("/fileUpload.do")
//    @ResponseBody
//    public String fileUpload(@RequestParam("file") MultipartFile file,
//                             HttpServletRequest request) throws Exception {
//    	logger.info("-----------------------------------------------start");
//        if (file == null || file.isEmpty()) {
//            return "파일이 없습니다.";
//        }
//
//        // 저장 폴더 생성
//        File uploadDir = new File(UPLOAD_PATH);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//
//    	logger.info("-----------------------------------------------middle");
//        // 중복 방지 파일명
//        String originalName = file.getOriginalFilename();
//        String savedName = UUID.randomUUID().toString() + "_" + originalName;
//
//        File saveFile = new File(UPLOAD_PATH + savedName);
//        file.transferTo(saveFile);
//    	logger.info("-----------------------------------------------end");
//
//        return "업로드 성공: " + savedName;
//    }
    //===========================================================================================================
    
    /**
     * 상품 수정 
     */
    
    @PostMapping("/product/{productCode}.do")
    public String updateProduct(
           @PathVariable("productCode") String productCode,
           @ModelAttribute ProductVO productVO,
           @RequestParam(value="imageFile", required=false) MultipartFile imageFile,
           @RequestParam(value="categoryListJson", required=false) String categoryListJson,
           @RequestParam(value="origin", required=false) String origin,
           HttpServletRequest request
    ) throws Exception {
//        logger.info("들어옴");

		if (imageFile != null && !imageFile.isEmpty()) {
		
		    //String uploadDir = "C:/upload/";
			String uploadDir = request.getServletContext().getRealPath("/photo/");
		
		    File dir = new File(uploadDir);
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		
		    String originalFilename = imageFile.getOriginalFilename();
		   // String saveName = UUID.randomUUID() + "_" + originalFilename;
		    String saveName = System.currentTimeMillis() + String.valueOf((int)(Math.random()*1000))+ "_" + originalFilename;
		
		    File saveFile = new File(uploadDir + saveName);
		    imageFile.transferTo(saveFile);
		    
		    productVO.setImage(origin+"/photo/"+saveName);
		    logger.info("파일 저장 완료: " + saveName);
		}

		categoryListJson = categoryListJson.replace("&quot;", "\"").replace("&amp;", "&");

		if (categoryListJson != null && !categoryListJson.isEmpty()) {
	        ObjectMapper mapper = new ObjectMapper();
	        List<CategoryVO> list =
	            mapper.readValue(categoryListJson,
	                new TypeReference<List<CategoryVO>>() {});
	        productVO.setCategoryList(list);
	        for(int i=0; i < list.size(); i++) {
	        	if(list.get(i).getIsNew().equals("Y")) {
	        	logger.info(list.get(i).getCategoryId() + " == " + list.get(i).getCategoryName() + " == " + list.get(i).getIsNew());
	        	CategoryVO category = list.get(i);
	            productService.updateCategory(category); 
	        	}
	        	
	        }
	        
	    }
		
		logger.info("rhs#######################################>>"+origin);
        productVO.setProductCode(productCode);
        logger.info("rhs######################kind#################>>"+productVO.getKind());
        if(productVO.getKind().equals("1")){
        	productService.insertProduct(productVO); 
        }else {
        	productService.updateProduct(productVO); 
        }

        return "success";
    }

    /**
     * 상품 삭제
     * @throws Exception 
     */
    @DeleteMapping("/product/{productCode}.do")
    public String deleteProduct(@PathVariable("productCode") String productCode) throws Exception {
        productService.deleteProduct(productCode);
        return "deleted";
    }
    
    /**
     * 로그인
     * @throws Exception 
     */
    @PostMapping("/login.do")
    public ResponseEntity<?> login(@RequestBody UserVO userVO, HttpSession session) throws Exception {
//		logger.info("rhs#######################################>>"+userVO.getUserName());
//		logger.info("rhs#######################################>>"+userVO.getPassword());

        // DB에서 사용자 조회했다고 가정
    	userVO = productService.login(userVO.getUserId(), userVO.getPassword());

        if (userVO != null) {
            //session.setAttribute("id", userVO.getUserName());
            session.setAttribute("loginUser", userVO);
            //return ResponseEntity.ok().build();
            //return ResponseEntity.ok(userVO);
            return ResponseEntity.ok(Map.of("loginUser", userVO));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    
    /**
     * 카카오 로그인
     * @throws Exception 
     */
    @PostMapping("/naverLogin.do")
    public ResponseEntity<?> naverLogin(@RequestBody UserVO userVO, HttpSession session) throws Exception {
//		logger.info("rhs#######################################>>"+userVO.getUserName());
//		logger.info("rhs#######################################>>"+userVO.getPassword());

        // DB에서 사용자 조회했다고 가정
    	userVO = productService.naverLogin(userVO.getKakaoId(), userVO.getEmail());

        if (userVO != null) {
            //session.setAttribute("id", userVO.getUserName());
            session.setAttribute("loginUser", userVO);
            //return ResponseEntity.ok().build();
            //return ResponseEntity.ok(userVO);
            return ResponseEntity.ok(Map.of("loginUser", userVO));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    
    /**
     * 카카오 로그인
     * @throws Exception 
     */
    @PostMapping("/kakaoLogin.do")
    public ResponseEntity<?> kakaoLogin(@RequestBody UserVO userVO, HttpSession session) throws Exception {
//		logger.info("rhs#######################################>>"+userVO.getUserName());
//		logger.info("rhs#######################################>>"+userVO.getPassword());

        // DB에서 사용자 조회했다고 가정
    	userVO = productService.kakaoLogin(userVO.getKakaoId(), userVO.getEmail());

        if (userVO != null) {
            //session.setAttribute("id", userVO.getUserName());
            session.setAttribute("loginUser", userVO);
            //return ResponseEntity.ok().build();
            //return ResponseEntity.ok(userVO);
            return ResponseEntity.ok(Map.of("loginUser", userVO));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    
    @PostMapping("logout.do")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // 세션 제거
        return ResponseEntity.ok().body(Map.of("message", "로그아웃 성공"));
    }
    
    @GetMapping("/session.do")
    public Map<String, Object> getSession(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("loginUser", session.getAttribute("loginUser"));
        return map;
    }
}
