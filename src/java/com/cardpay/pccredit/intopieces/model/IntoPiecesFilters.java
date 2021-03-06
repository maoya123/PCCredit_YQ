package com.cardpay.pccredit.intopieces.model;

import java.math.BigDecimal;
import java.util.Date;

import com.wicresoft.jrad.base.database.dao.business.BusinessFilter; 


public class IntoPiecesFilters extends BusinessFilter{
	private String sorting;
	public String getSorting() {
		return sorting;
	}
	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
	private Date creatime;
	private Date creatime1;
	private String id;//进件编号
	private String customerId;//客户ID(外键)
	private String chineseName;//客户名称
	
    private String productId;//产品Id
    private String productName; //产品名称
    private String cardId;//证件号码
    private String applyQuota;//申请额度
    private String actualQuote;//发放额度
    private String status;//申请额度
    private String statusName;
    
    private String nodeName;
    
    private String tyCustomerId;
    
    private String decisionRefuseReason; //备注
    
    private String displayName; //客户经理
    private String userId;//客户经理id
    private String groupName; //客户经理组长
    
    private String jjh; //对应银行借据号
    private String jkrq; //对应银行借款日期
    private Float dkye;
    private Float bnqx;
    private Float bwqx;
    private String reqlmt;
    private String PRODCREDITRANGE;
    private BigDecimal actualAmt;
    
    private BigDecimal amt;
    
    private String refusqlReason;
    private String fallBackReason;
    
    private String repayStatus;
    
    private String finalApproval;
    
    private String managerName;
    
    private String organName;
    private Float yxje;
    
 private String final_approval;
    
    private String actual_quote;
    
    private String REFUSAL_REASON;
    private Integer zsw;
    private String applitionId;
	public Date getCreatime() {
		return creatime;
	}
	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}
	public Date getCreatime1() {
		return creatime1;
	}
	public void setCreatime1(Date creatime1) {
		this.creatime1 = creatime1;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getApplyQuota() {
		return applyQuota;
	}
	public void setApplyQuota(String applyQuota) {
		this.applyQuota = applyQuota;
	}
	public String getActualQuote() {
		return actualQuote;
	}
	public void setActualQuote(String actualQuote) {
		this.actualQuote = actualQuote;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getTyCustomerId() {
		return tyCustomerId;
	}
	public void setTyCustomerId(String tyCustomerId) {
		this.tyCustomerId = tyCustomerId;
	}
	public String getDecisionRefuseReason() {
		return decisionRefuseReason;
	}
	public void setDecisionRefuseReason(String decisionRefuseReason) {
		this.decisionRefuseReason = decisionRefuseReason;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getJjh() {
		return jjh;
	}
	public void setJjh(String jjh) {
		this.jjh = jjh;
	}
	public String getJkrq() {
		return jkrq;
	}
	public void setJkrq(String jkrq) {
		this.jkrq = jkrq;
	}
	public Float getDkye() {
		return dkye;
	}
	public void setDkye(Float dkye) {
		this.dkye = dkye;
	}
	public Float getBnqx() {
		return bnqx;
	}
	public void setBnqx(Float bnqx) {
		this.bnqx = bnqx;
	}
	public Float getBwqx() {
		return bwqx;
	}
	public void setBwqx(Float bwqx) {
		this.bwqx = bwqx;
	}
	public String getReqlmt() {
		return reqlmt;
	}
	public void setReqlmt(String reqlmt) {
		this.reqlmt = reqlmt;
	}
	public String getPRODCREDITRANGE() {
		return PRODCREDITRANGE;
	}
	public void setPRODCREDITRANGE(String pRODCREDITRANGE) {
		PRODCREDITRANGE = pRODCREDITRANGE;
	}
	public BigDecimal getActualAmt() {
		return actualAmt;
	}
	public void setActualAmt(BigDecimal actualAmt) {
		this.actualAmt = actualAmt;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getRefusqlReason() {
		return refusqlReason;
	}
	public void setRefusqlReason(String refusqlReason) {
		this.refusqlReason = refusqlReason;
	}
	public String getFallBackReason() {
		return fallBackReason;
	}
	public void setFallBackReason(String fallBackReason) {
		this.fallBackReason = fallBackReason;
	}
	public String getRepayStatus() {
		return repayStatus;
	}
	public void setRepayStatus(String repayStatus) {
		this.repayStatus = repayStatus;
	}
	public String getFinalApproval() {
		return finalApproval;
	}
	public void setFinalApproval(String finalApproval) {
		this.finalApproval = finalApproval;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public Float getYxje() {
		return yxje;
	}
	public void setYxje(Float yxje) {
		this.yxje = yxje;
	}
	public String getFinal_approval() {
		return final_approval;
	}
	public void setFinal_approval(String final_approval) {
		this.final_approval = final_approval;
	}
	public String getActual_quote() {
		return actual_quote;
	}
	public void setActual_quote(String actual_quote) {
		this.actual_quote = actual_quote;
	}
	public String getREFUSAL_REASON() {
		return REFUSAL_REASON;
	}
	public void setREFUSAL_REASON(String rEFUSAL_REASON) {
		REFUSAL_REASON = rEFUSAL_REASON;
	}
	public Integer getZsw() {
		return zsw;
	}
	public void setZsw(Integer zsw) {
		this.zsw = zsw;
	}
	public String getApplitionId() {
		return applitionId;
	}
	public void setApplitionId(String applitionId) {
		this.applitionId = applitionId;
	}
    
    
    

	
}
