/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.shop.service.CategoryVO;
import egovframework.shop.service.ProductService;
import egovframework.shop.service.ProductVO;
import egovframework.shop.service.UserVO;
import jakarta.annotation.Resource;

/**
 * @Class Name : EgovProductServiceImpl.java
 * @Description : Product Business Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("productService")
@Transactional
public class ProductServiceImpl extends EgovAbstractServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	/** ProductMapper */
//	@Resource(name = "ProductMapper")
//	private ProductMapper ProductMapper;

    @Autowired
    private ProductMapper productMapper;   // ⭐ Bean 주입

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	/**
	 * 제품을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ProductVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	@Override
	@Transactional
	public void insertProduct(ProductVO vo) throws Exception {
		LOGGER.debug(vo.toString());

//		/** ID Generation Service */
//		String id = egovIdGnrService.getNextStringId();
//		//vo.setId(id);
//		LOGGER.debug(vo.toString());

		productMapper.insertProduct(vo);
	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	@Transactional
	public void updateProduct(ProductVO vo) throws Exception {
		productMapper.updateProduct(vo);
	}

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ProductVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	@Transactional
	public void deleteProduct(String productCode) throws Exception {
		productMapper.deleteProduct(productCode);
	}

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ProductVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public ProductVO selectProduct(ProductVO vo) throws Exception {
		ProductVO resultVO = productMapper.selectProduct(vo);
//		if (resultVO == null)
//			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * 글 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	@Override
	public List<?> selectProductList(ProductVO vo) throws Exception {
		return productMapper.selectProductList(vo);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	@Override
	public int selectProductListTotCnt(ProductVO vo) {
		return productMapper.selectProductListTotCnt(vo);
	}

	@Override
	public List<CategoryVO> selectCategory() throws Exception {
		// TODO Auto-generated method stub
		return productMapper.selectCategory();
	}
	
	/**
	 * 카테고릐를 수정한다.
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	@Transactional
	public void updateCategory(CategoryVO vo) throws Exception {
		productMapper.updateCategory(vo);
	}

	/**
	 * 로그인 한다.
	 * @param vo - 수정할 정보가 담긴 id, password
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public UserVO login(String id, String password) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		return productMapper.selectLogin(map);
	}

	/**
	 * 카카오 로그인 한다.
	 * @param vo - 수정할 정보가 담긴 id, password
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public UserVO kakaoLogin(String kakaoId, String email) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("kakaoId", kakaoId);
		//map.put("email", email);
		return productMapper.selectKakaoLogin(map);
	}

	/**
	 * 네이버 로그인 한다.
	 * @param vo - 수정할 정보가 담긴 id, password
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public UserVO naverLogin(String naverId, String email) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("naverId", naverId);
		//map.put("email", email);
		return productMapper.selectNaverLogin(map);
	}

}
