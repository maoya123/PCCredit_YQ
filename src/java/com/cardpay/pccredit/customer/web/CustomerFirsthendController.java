package com.cardpay.pccredit.customer.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardpay.pccredit.customer.filter.CustomerInforFilter;
import com.cardpay.pccredit.customer.model.CIPERSONBASINFO;
import com.cardpay.pccredit.customer.model.CIPERSONBASINFOCOPY;
import com.cardpay.pccredit.customer.model.CIPERSONBASINFOCOPYFORM;
import com.cardpay.pccredit.customer.model.CIPERSONFAMILY;
import com.cardpay.pccredit.customer.model.CustomerFirsthendBase;
import com.cardpay.pccredit.customer.model.CustomerFirsthendFamilyCc;
import com.cardpay.pccredit.customer.model.CustomerFirsthendFamilyCy;
import com.cardpay.pccredit.customer.model.CustomerFirsthendManage;
import com.cardpay.pccredit.customer.model.CustomerFirsthendSafe;
import com.cardpay.pccredit.customer.model.CustomerFirsthendStudy;
import com.cardpay.pccredit.customer.model.CustomerFirsthendWork;
import com.cardpay.pccredit.customer.model.CustomerInfor;
import com.cardpay.pccredit.customer.service.CustomerInforService;
import com.cardpay.pccredit.customer.service.MaintenanceService;
import com.cardpay.pccredit.intopieces.filter.IntoPiecesFilter;
import com.cardpay.pccredit.intopieces.model.IntoPieces;
import com.cardpay.pccredit.intopieces.service.CustomerApplicationIntopieceWaitService;
import com.cardpay.pccredit.intopieces.service.IntoPiecesService;
import com.cardpay.pccredit.intopieces.web.CustomerApplicationIntopieceWaitForm;
import com.cardpay.pccredit.jnpad.model.MonthlyStatisticsModel;
import com.cardpay.pccredit.jnpad.service.MonthlyStatisticsService;
import com.cardpay.pccredit.manager.filter.ManagerSalaryFilter;
import com.cardpay.pccredit.manager.web.AccountManagerParameterForm;
import com.cardpay.pccredit.product.service.ProductService;
import com.wicresoft.jrad.base.auth.IUser;
import com.wicresoft.jrad.base.auth.JRadModule;
import com.wicresoft.jrad.base.auth.JRadOperation;
import com.wicresoft.jrad.base.database.model.QueryResult;
import com.wicresoft.jrad.base.web.JRadModelAndView;
import com.wicresoft.jrad.base.web.controller.BaseController;
import com.wicresoft.jrad.base.web.result.JRadPagedQueryResult;
import com.wicresoft.jrad.base.web.result.JRadReturnMap;
import com.wicresoft.jrad.base.web.security.LoginManager;
import com.wicresoft.jrad.base.web.utility.WebRequestHelper;
import com.wicresoft.util.spring.Beans;
import com.wicresoft.util.spring.mvc.mv.AbstractModelAndView;
import com.wicresoft.util.web.RequestHelper;

/**
 * 
 * @author 贺珈
 *客户原始信息
 */
@Controller
@RequestMapping("/customer/firsthend/*")
@JRadModule("customer.firsthend")
public class CustomerFirsthendController extends BaseController{
	@Autowired
	private CustomerApplicationIntopieceWaitService customerApplicationIntopieceWaitService;
	@Autowired
	private IntoPiecesService intoPiecesService;
	
	@Autowired
	private CustomerInforService customerInforService;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private MonthlyStatisticsService StatisticsService;
	@Autowired
	private MaintenanceService maintenanceService;
	/**
	 * 浏览页面
	 * 
	 * @param filter
	 * @param request
	 * @return
	
*/
	@ResponseBody
	@RequestMapping(value = "browse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView browse(@ModelAttribute CustomerInforFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
		//filter.setUserId(user.getId());
		//查询客户经理
		QueryResult<CustomerInfor> result =null;
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customerfirsthend_browse", request);
		/*List<AccountManagerParameterForm> forms = maintenanceService.findSubListManagerByManagerId(user);*/
		List<AccountManagerParameterForm> forms = new ArrayList<AccountManagerParameterForm>();
		/*if(!StringUtils.isEmpty(filter.getCustomerManagerId())){
			result = customerInforService.findCustomerInforByFilter(filter);
		}else{
			if(forms.size()>0){
				filter.setCustomerManagerIds(forms);
				result = customerInforService.findCustomerInforByFilter(filter);
			}else{
				//直接返回页面
				result = customerInforService.findCustomerInforByFilter(filter);
			}
		}*/
		//QueryResult<CustomerInfor> result = customerInforService.findCustomerInforByFilter(filter);
		if(user.getUserType()==4 || user.getUserType()==5){
			List<CustomerApplicationIntopieceWaitForm> list=customerApplicationIntopieceWaitService.findSpRy(user.getId());
			for(int i=0;i<list.size();i++){
				AccountManagerParameterForm from=new AccountManagerParameterForm();
				from.setDisplayName(list.get(i).getDisplayName());
				from.setUserId(list.get(i).getUserId());
				forms.add(i, from);
			}
			filter.setCustomerManagerId(user.getId());
		}else if(user.getUserType()==1){
			  List<MonthlyStatisticsModel> resultModel=null;
			     resultModel=StatisticsService.findxzz(user.getId());
			     if(resultModel.size()>0){
			    	 for(int i=0;i<resultModel.size();i++){
			    			AccountManagerParameterForm from=new AccountManagerParameterForm();
							from.setDisplayName(resultModel.get(i).getName());
							from.setUserId(resultModel.get(i).getUserId());
							forms.add(i, from);
			    	 }
			    	 filter.setCustomerManagerId(user.getId());
			     }else{
			    	 filter.setUserId(user.getId());
			     }
		}result = customerInforService.findCustomerInforByFilter(filter);
		JRadPagedQueryResult<CustomerInfor> pagedResult = new JRadPagedQueryResult<CustomerInfor>(filter, result);
		
		mv.addObject(PAGED_RESULT, pagedResult);
		mv.addObject("customerName", user.getDisplayName());
		mv.addObject("forms", forms);
		return mv;
	}
	 
	/**
	 * 查看客户原始信息
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "showInfo.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView showInfo(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_1", request);
		String customerInforId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerFirsthendBase base = customerInforService.findCustomerFirsthendById(customerInforId);
			mv.addObject("customerInfor", base);
			mv.addObject("customerNm", base.getKhnm());
		}
		return mv;
	}
	
	/**
	 * 基本信息
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_1.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_1(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_1", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			CustomerFirsthendBase base = customerInforService.findCustomerFirsthendByNm(customerNm);
			mv.addObject("customerInfor", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	/**
	 * 信用信息
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_2.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_2(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_2", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			CustomerFirsthendBase base = customerInforService.findCustomerFirsthendByNm(customerNm);
			mv.addObject("customerInfor", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	/**
	 * 其他信息
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_3.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_3(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_3", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			CustomerFirsthendBase base = customerInforService.findCustomerFirsthendByNm(customerNm);
			mv.addObject("customerInfor", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	/**
	 * 家庭关系
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_4.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_4(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_4", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			List<CIPERSONFAMILY> base = customerInforService.findFamilyByCustId(customerNm);
			mv.addObject("result", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	/**
	 * 家庭关系
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_5.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_5(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_5", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			//社会类型
			String type="2";
			List<CustomerFirsthendFamilyCy> base = customerInforService.findFamilyByNm(customerNm, type);
			mv.addObject("result", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	/**
	 * 家庭财产
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_6.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_6(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_6", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			List<CustomerFirsthendFamilyCc> base = customerInforService.findFamilyCcByNm(customerNm);
			mv.addObject("result", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	/**
	 * 生产经营
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_7.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_7(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_7", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			List<CustomerFirsthendManage> base = customerInforService.findManageByNm(customerNm);
			mv.addObject("result", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	/**
	 * 学习情况
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_8.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_8(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_8", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			List<CustomerFirsthendStudy> base = customerInforService.findStudyByNm(customerNm);
			mv.addObject("result", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	/**
	 *工作情况
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_9.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_9(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_9", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			List<CustomerFirsthendWork> base = customerInforService.findWorkByNm(customerNm);
			mv.addObject("result", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	/**
	 *入保信息
	 * 
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_10.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_10(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_10", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			List<CustomerFirsthendSafe> base = customerInforService.findSafeByNm(customerNm);
			mv.addObject("result", base);
			mv.addObject("customerNm", customerNm);
		}
		return mv;
	}
	
	
	
	
	/**
	 * 查看客户原始信息
	 * jn
	 * @param request
	 * @return
	*//*
	@ResponseBody
	@RequestMapping(value = "showInfoJn.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView showInfoJn(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_11", request);
		String customerInforId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(customerInforId)) {
			CIPERSONBASINFO base = customerInforService.findCustomerFirsthendBase(customerInforId);
			mv.addObject("customerInfor", base);
			mv.addObject("customerNm", base.getId());
		}
		return mv;
	}*/
	/**
	 * 查看客户原始信息
	 * jn
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "showInfoJn.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView showInfoJn(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_11", request);
		String customerInforId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerFirsthendBase base=customerInforService.findCustomerFirsthendBase(customerInforId);
			//CIPERSONBASINFO base = customerInforService.findCIPERSONBASINFO(customerInforId);
			mv.addObject("customerInfor", base);
			mv.addObject("customerNm", base.getId());
			mv.addObject("customercardId", base.getZjhm());
		}
		return mv;
	}
	/**
	 * 基本信息
	 * jn
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "iframe_11.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView iframe_11(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_11", request);
		String customerNm = request.getParameter("id");
		if (StringUtils.isNotEmpty(customerNm)) {
			CustomerFirsthendBase base = customerInforService.findCustomerFirsthendBase(customerNm);
			mv.addObject("customerInfor", base);
			mv.addObject("customerNm", base.getId());
			mv.addObject("customercardId", base.getZjhm());
		}
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "musidataBrowse.page", method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.BROWSE)
	public AbstractModelAndView musidataBrowse(@ModelAttribute CustomerInforFilter filter, HttpServletRequest request) {
		filter.setRequest(request);
		IUser user = Beans.get(LoginManager.class).getLoggedInUser(request);
		JRadModelAndView mv = new JRadModelAndView("/customer/customerLedger/zrrtz", request);
		return mv;
	}
	
	
	/**
	 * 修改客户原始信息
	 * @param request
	 * @return
	*//*
	@ResponseBody
	@RequestMapping(value = "showCustomerInfoJn.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView updateCustomerInfoJn(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_12", request);
		String customerInforId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(customerInforId)) {
			CIPERSONBASINFOCOPY base = customerInforService.findCIPERSONBASINFOCOPY(customerInforId);
			if(base == null){
				//复制
				customerInforService.findCIPERSONBASINFOCOPYISEXIT(customerInforId);
				//查询
				CIPERSONBASINFOCOPY copy = customerInforService.findCIPERSONBASINFOCOPY(customerInforId);
				mv.addObject("customerInfor", copy);
				mv.addObject("customerNm", copy.getId());
			}else{
				mv.addObject("customerInfor", base);
				mv.addObject("customerNm", base.getId());
			}
		}
		return mv;
		
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_12", request);
		String customerInforId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(customerInforId)) {
			CustomerInfor cust = customerInforService.findBasicCustomerInformation(customerInforId);
			mv.addObject("customerInfor", cust);
			mv.addObject("customerNm", cust.getId());
		}
		return mv;
	}*/
	/**
	 * 修改客户原始信息
	 * @param request
	 * @return
	*/
	@ResponseBody
	@RequestMapping(value = "showCustomerInfoJn.page")
	@JRadOperation(JRadOperation.CREATE)
	public AbstractModelAndView updateCustomerInfoJn(HttpServletRequest request) {
		JRadModelAndView mv = new JRadModelAndView("/customer/customerFirsthend/customer_iframe_12", request);
		String customerInforId = RequestHelper.getStringValue(request, ID);
		if (StringUtils.isNotEmpty(customerInforId)) {
			CIPERSONBASINFOCOPY base = customerInforService.findCIPERSONBASINFOCOPY(customerInforId);
			if(base == null){
				//复制
				//customerInforService.findCIPERSONBASINFOCOPYISEXIT(customerInforId);
				//查询
				CustomerFirsthendBase copy=customerInforService.findCustomerFirsthendBase(customerInforId);
				if(copy.getHyzk()==null || copy.getHyzk()==""){
					copy.setHyzk("无");	
				}
				if(copy.getXxdz()==null || copy.getXxdz()==""){
					copy.setXxdz("无");	
				}
				if(copy.getGtspmc()==null || copy.getGtspmc()==""){
					copy.setGtspmc("无");	
				}
				if(copy.getSj()==null || copy.getSj()==""){
					copy.setSj("无");
				}
				if(copy.getJtdh()==null || copy.getJtdh()==""){
					copy.setJtdh("无");
				}
				if(copy.getHjdz()==null || copy.getHjdz()==""){
					copy.setHjdz("无");
				}
				if(copy.getXw()==null || copy.getXw()==""){
					copy.setXw("无");
				}
				if(copy.getXl()==null || copy.getXl()==""){
					copy.setXl("无");
				}
				//CIPERSONBASINFOCOPY copy = customerInforService.findCIPERSONBASINFOCOPY(customerInforId);
				mv.addObject("customerInfor", copy);
				mv.addObject("customerNm", copy.getId());
			}else{
				mv.addObject("customerInfor", base);
				mv.addObject("customerNm", base.getId());
			}
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "exportCustomerData.json",method = { RequestMethod.GET })
	@JRadOperation(JRadOperation.CHANGE)
	public JRadReturnMap exportCustomerData(@ModelAttribute CustomerInforFilter filter, HttpServletRequest request,HttpServletResponse response) {
		JRadReturnMap returnMap = new JRadReturnMap();
		filter.setRequest(request);
		returnMap.setSuccess(true);
		if (returnMap.isSuccess()) {
			try {
				customerInforService.exportCustomerData(filter,response);
				
			}
			catch (Exception e) {
				return WebRequestHelper.processException(e);
			}
		}
		return returnMap;
	}
}
