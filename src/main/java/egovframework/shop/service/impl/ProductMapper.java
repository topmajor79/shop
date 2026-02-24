/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.shop.service.CategoryVO;
import egovframework.shop.service.ProductVO;
import egovframework.shop.service.UserVO;

/**
 * Product에 관한 데이터처리 매퍼 클래스
 *
 * @author  표준프레임워크센터
 * @since 2014.01.24
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2014.01.24        표준프레임워크센터          최초 생성
 *
 * </pre>
 */
@EgovMapper("ProductMapper")
public interface ProductMapper {

	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ProductVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	void insertProduct(ProductVO vo) throws Exception;

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return void형
	 * @exception Exception
	 */
	void updateProduct(ProductVO vo) throws Exception;

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ProductVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteProduct(String productCode) throws Exception;

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ProductVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	ProductVO selectProduct(ProductVO vo) throws Exception;

	/**
	 * 글 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<ProductVO> selectProductList(ProductVO vo) throws Exception;

	/**
	 * 글 총 갯수를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	int selectProductListTotCnt(ProductVO vo);
	
	/**
	 * 글을 삭제한다.
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return void형
	 * @exception Exception
	 */

    void deleteProduct(Long id);
    

	/**
	 * 카테고리 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    List<CategoryVO> selectCategory();    

	/**
	 * 카테고리를 수정한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    void updateCategory(CategoryVO vo);
    

	/**
	 * 로그인 한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */  
    UserVO selectLogin(Map map);
    

	/**
	 * 카카오 로그인 한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */  
    UserVO selectKakaoLogin(Map map);
    

	/**
	 * 네이버 로그인 한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */  
    UserVO selectNaverLogin(Map map);

}
