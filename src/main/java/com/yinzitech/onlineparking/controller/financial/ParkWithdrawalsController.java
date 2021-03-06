/**
 * Project Name:onlineParking
 * File Name:ParkWithdrawals.java
 * Package Name:withdrawals
 * Date:2015年9月28日上午10:44:02
 * Copyright (c) 2015, ziheng915@vip.qq.com All Rights Reserved.
 *
*/

package com.yinzitech.onlineparking.controller.financial;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinzitech.onlineparking.service.parkingInfo.ParkingInfoService;
import com.yinzitech.onlineparking.service.parkingSys.ParkingManagerService;

import net.sf.json.JSONObject;

/**
 * ClassName:ParkWithdrawals <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 提现
 * Date:     2015年9月28日 上午10:44:02 <br/>
 * @author  xumingyue
 * @version  
 * @since    JDK 1.8u60
 * @see 	 
 */
@Controller
public class ParkWithdrawalsController {
	
	@Autowired
	ParkingManagerService pmService ;  //用户Service
	
	@Autowired
	ParkingInfoService pInfoService ; //停车场信息Service
	
	
	/**
	 * toRunWaterRecord:提现
	 * @author xumingyue
	 * @return
	 * @since JDK 1.8u60
	 */
		@RequestMapping(value="toWithdrawals")
		public ModelAndView toRunWaterRecord(HttpServletRequest request){
			String uID=  request.getParameter("userID") ; //(1)首先获取UID  uID存在seesion中 
			 String userInfo =   pmService.getParkingManagerByParkingManagerId(uID) ;//(2) 通过uID
			JSONObject obj = JSONObject.fromObject(userInfo); // (3)获取停车场ID
			JSONObject obj1 = obj.getJSONObject("datas");
			String parkingInfoId =(String) obj1.get("parkingInfoId"); //获取停车场id
			
			//通过停车场id获取停车场信息   然后在前台获取停车名称
			String parkInfo = pInfoService.getParkingInfoById(parkingInfoId) ;
			
			
			ModelAndView mv = new ModelAndView() ;
			mv.setViewName("parking/finance/withdrawals");
			mv.addObject("parkInfo",parkInfo ) ;
			return mv ;
		}
}

