package com.cardpay.pccredit.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cardpay.pccredit.intopieces.model.CustomerSqInfo;

import sun.misc.BASE64Encoder;
/**
 * @功能描述 POI 读取 Excel 转 HTML 支持 03xls 和 07xlsx 版本  包含样式
 */
public class JXLReadExcel {
	/*public static void main(String[] args) {
		JXLReadExcel jxl = new JXLReadExcel();
		String path = "C:\\Users\\Administrator\\Desktop\\小微贷标准版0806模板---周素平12万.xlsx";
		jxl.readExcelToHtml(path, 0, true);
	}*/
	
	
	 public static CustomerSqInfo CustomerSqInfo=new CustomerSqInfo();
    /**
     * 程序入口方法
     * @param filePath 文件的路径
     * @param isWithStyle 是否需要表格样式 包含 字体 颜色 边框 对齐方式
     * @return <table>...</table> 字符串
     */
    public String[] readExcelToHtml(String filePath, boolean isWithStyle){
      	String sheet[] = new String[19];
        InputStream is = null;
        String approveValue="";
//        String htmlExcel = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            File sourcefile = new File(filePath);
            is = new FileInputStream(sourcefile);
            Workbook wb = WorkbookFactory.create(is);
            for(int i=0;i<wb.getNumberOfSheets();i++)
            {
            	if(wb.getSheetAt(i).getSheetName().indexOf("建议")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(0);
                        Row row = st.getRow(2);
                        Cell cell = row.getCell(4);
                        approveValue = getCellValue(cell);
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jy,ImportParameter.editAble_jy,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(0);
                        Row row = st.getRow(2);
                        Cell cell = row.getCell(4);
                        approveValue = getCellValue(cell);
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jy,ImportParameter.editAble_jy,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[0] = content_base64;
            	}
            	System.out.println("sdhfwehfiowepfweew"+approveValue);
            	if(wb.getSheetAt(i).getSheetName().indexOf("基本状况")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jbzk,ImportParameter.editAble_jbzk,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jbzk,ImportParameter.editAble_jbzk,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[1] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("经营状态")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jyzk,ImportParameter.editAble_jyzk,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jyzk,ImportParameter.editAble_jyzk,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[2] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("生存状态")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_sczt,ImportParameter.editAble_sczt,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_sczt,ImportParameter.editAble_sczt,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[3] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("道德品质")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ddpz,ImportParameter.editAble_ddpz,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ddpz,ImportParameter.editAble_ddpz,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[4] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("资产负债")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_zf,ImportParameter.editAble_fz,true);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_zf,ImportParameter.editAble_fz,true);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[5] = content_base64;
            	}
            	else if(wb.getSheetAt(i).getSheetName().indexOf("利润简表")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_lrjb,ImportParameter.editAble_lrjb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_lrjb,ImportParameter.editAble_lrjb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[6] = content_base64;
            	}
  				else if(wb.getSheetAt(i).getSheetName().indexOf("标准利润表")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_bzlrb,ImportParameter.editAble_bzlrb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_bzlrb,ImportParameter.editAble_bzlrb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[7] = content_base64;
            	}
  				else if(wb.getSheetAt(i).getSheetName().indexOf("主营业务明细表")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_zyyw,ImportParameter.editAble_zyyw,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_zyyw,ImportParameter.editAble_zyyw,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[8] = content_base64;
            	}
  				else if(wb.getSheetAt(i).getSheetName().indexOf("现金流量表")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_xjllb,ImportParameter.editAble_xjllb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_xjllb,ImportParameter.editAble_xjllb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[9] = content_base64;
  				}
  				else if(wb.getSheetAt(i).getSheetName().indexOf("交叉检验")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jc,ImportParameter.editAble_jc,true);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jc,ImportParameter.editAble_jc,true);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[10] = content_base64;
  				}
            	
  				else if(wb.getSheetAt(i).getSheetName().indexOf("点货单")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_dhd,ImportParameter.editAble_dhd,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_dhd,ImportParameter.editAble_dhd,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[11] = content_base64;
  				}
  				else if(wb.getSheetAt(i).getSheetName().indexOf("固定资产")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_gdzc,ImportParameter.editAble_gdzc,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_gdzc,ImportParameter.editAble_gdzc,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[12] = content_base64;
  				}
  				else if(wb.getSheetAt(i).getSheetName().indexOf("应付预收")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_yfys,ImportParameter.editAble_yfys,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_yfys,ImportParameter.editAble_yfys,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[13] = content_base64;
  				}
  				else if(wb.getSheetAt(i).getSheetName().indexOf("应收预付")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ysyf,ImportParameter.editAble_ysyf,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ysyf,ImportParameter.editAble_ysyf,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[14] = content_base64;
  				}
  				else if(wb.getSheetAt(i).getSheetName().indexOf("流水分析")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_lsfx,ImportParameter.editAble_lsfx,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_lsfx,ImportParameter.editAble_lsfx,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[15] = content_base64;
  				}
  				else if(wb.getSheetAt(i).getSheetName().indexOf("决议表")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jyb,ImportParameter.editAble_jyb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jyb,ImportParameter.editAble_jyb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[16] = content_base64;
  				}else if(wb.getSheetAt(i).getSheetName().indexOf("保证人 信 息 表")>=0){
  					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_dbrxxb,ImportParameter.editAble_dbrxxb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_dbrxxb,ImportParameter.editAble_dbrxxb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
  					sheet[17] = content_base64;
  				}
            	sheet[18] = approveValue;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sheet;/*
        
    	String sheet[] = new String[18];
        InputStream is = null;
        String approveValue="";
//        String htmlExcel = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            File sourcefile = new File(filePath);
            is = new FileInputStream(sourcefile);
            Workbook wb = WorkbookFactory.create(is);
            for(int i=0;i<wb.getNumberOfSheets();i++)
            {
            	System.out.println(wb.getSheetAt(i).getSheetName());
            	if(wb.getSheetAt(i).getSheetName().indexOf("建议")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(0);
                        Row row = st.getRow(2);
                        Cell cell = row.getCell(4);
                        approveValue = getCellValue(cell);
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jy,ImportParameter.editAble_jy,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(0);
                        Row row = st.getRow(2);
                        Cell cell = row.getCell(4);
                        approveValue = getCellValue(cell);
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jy,ImportParameter.editAble_jy,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[0] = content_base64;
            	}
            	if(wb.getSheetAt(i).getSheetName().indexOf("基本状况")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jbzk,ImportParameter.editAble_jbzk,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jbzk,ImportParameter.editAble_jbzk,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[1] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("经营状态")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jyzk,ImportParameter.editAble_jyzk,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jyzk,ImportParameter.editAble_jyzk,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[2] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("生存状态")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_sczt,ImportParameter.editAble_sczt,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_sczt,ImportParameter.editAble_sczt,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[3] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("道德品质")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ddpz,ImportParameter.editAble_ddpz,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ddpz,ImportParameter.editAble_ddpz,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[4] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("资产负债")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_zf,ImportParameter.editAble_fz,true);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_zf,ImportParameter.editAble_fz,true);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[5] = content_base64;
            	}
            	else if(wb.getSheetAt(i).getSheetName().indexOf("利润简表")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_lrjb,ImportParameter.editAble_lrjb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_lrjb,ImportParameter.editAble_lrjb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[6] = content_base64;
            	}
				else if(wb.getSheetAt(i).getSheetName().indexOf("标准利润表")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_bzlrb,ImportParameter.editAble_bzlrb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_bzlrb,ImportParameter.editAble_bzlrb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[7] = content_base64;
            	}
				else if(wb.getSheetAt(i).getSheetName().indexOf("主营业务明细表")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_zyyw,ImportParameter.editAble_zyyw,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_zyyw,ImportParameter.editAble_zyyw,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[8] = content_base64;
            	}
				else if(wb.getSheetAt(i).getSheetName().indexOf("现金流量表")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_xjllb,ImportParameter.editAble_xjllb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_xjllb,ImportParameter.editAble_xjllb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[9] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("交叉检验")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jc,ImportParameter.editAble_jc,true);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jc,ImportParameter.editAble_jc,true);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[10] = content_base64;
				}
            	
				else if(wb.getSheetAt(i).getSheetName().indexOf("点货单")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_dhd,ImportParameter.editAble_dhd,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_dhd,ImportParameter.editAble_dhd,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[11] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("固定资产")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_gdzc,ImportParameter.editAble_gdzc,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_gdzc,ImportParameter.editAble_gdzc,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[12] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("应付预收")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_yfys,ImportParameter.editAble_yfys,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_yfys,ImportParameter.editAble_yfys,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[13] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("应收预付")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ysyf,ImportParameter.editAble_ysyf,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ysyf,ImportParameter.editAble_ysyf,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[14] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("流水分析")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_lsfx,ImportParameter.editAble_lsfx,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_lsfx,ImportParameter.editAble_lsfx,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[15] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("决议表")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jyb,ImportParameter.editAble_jyb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jyb,ImportParameter.editAble_jyb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[16] = content_base64;
				}else if(wb.getSheetAt(i).getSheetName().indexOf("担 保 人 信 息 表")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_dbrxxb,ImportParameter.editAble_dbrxxb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_dbrxxb,ImportParameter.editAble_dbrxxb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[18] = content_base64;
				}
            	sheet[17] = approveValue;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sheet;
    */}
    
    
    
    public Map<String, String> getExcelInfo(Workbook wb,int index,boolean isWithStyle,String[] rowAndcol,String[] editAble,boolean averageWidth) throws Exception{
        try {
     	   int maxRow = Integer.parseInt(rowAndcol[0])-1;//最大行
     	   String maxCol = rowAndcol[1];//最大列
     	   
     	   StringBuffer sb = new StringBuffer();
     	   Sheet sheet = wb.getSheetAt(index);//获取第一个Sheet的内容
     	   int lastRowNum = sheet.getLastRowNum();
     	   if(lastRowNum>maxRow){
     		   lastRowNum = maxRow;
     	   }
     	   Map<String, String> map[] = getRowSpanColSpanMap(sheet);
     	   sb.append("<table id='MyTable' style='border-collapse:collapse;' width='100%'>");
     	   Row row = null;        //兼容
     	   Cell cell = null;    //兼容
     	   
     	   Map<String, String> resultMap = new HashMap<String, String>();
     	   for (int rowNum = sheet.getFirstRowNum(); rowNum <= lastRowNum; rowNum++) {
     		   row = sheet.getRow(rowNum);
     		   if (row == null) {
     			   sb.append("<tr><td > &nbsp;</td></tr>");
     			   continue;
     		   }
     		   sb.append("<tr>");
     		   int lastColNum = row.getLastCellNum();
     		   if(lastColNum > columnToIndex(maxCol)){
     			   lastColNum = columnToIndex(maxCol);
     		   }
     		   for (int colNum = 0; colNum < lastColNum; colNum++) {
     			   cell = row.getCell(colNum);
     			   if (cell == null) {    //特殊情况 空白的单元格会返回null
     				   sb.append("<td>&nbsp;</td>");
     				   continue;
     			   }
     			   cell.setCellType(Cell.CELL_TYPE_STRING);
 					String	stringValue = getCellValue(cell);
 					System.out.println(stringValue);
     			   if (map[0].containsKey(rowNum + "," + colNum)) {
     				   String pointString = map[0].get(rowNum + "," + colNum);
     				   map[0].remove(rowNum + "," + colNum);
     				   int bottomeRow = Integer.valueOf(pointString.split(",")[0]);
     				   int bottomeCol = Integer.valueOf(pointString.split(",")[1]);
     				   int rowSpan = bottomeRow - rowNum + 1;
     				   int colSpan = bottomeCol - colNum + 1;
     				   sb.append("<td rowspan= '" + rowSpan + "' colspan= '"+ colSpan + "' ");
     			   } else if (map[1].containsKey(rowNum + "," + colNum)) {
     				   map[1].remove(rowNum + "," + colNum);
     				   continue;
     			   } else {
     				   sb.append("<td ");
     			   }
     			   
     			   String tmp = indexToColumn(colNum+1) +(rowNum+1);
     			   sb.append("name='"+tmp+"' ");
     			   if(editAble != null && Arrays.asList(editAble).contains(tmp)){//判断是否可编辑
     				   sb.append("onclick='return EditCell();' ");
     			   }
     			   
     			   //判断是否需要样式
     			   if(isWithStyle){
     				   dealExcelStyle(wb, sheet, cell, sb,averageWidth);//处理单元格样式
     			   }
     			   
     			   sb.append(">");
     			   
     			   if (stringValue == null || "".equals(stringValue.trim())) {
     				   sb.append("&nbsp;");
     			   } else {
     				   // 将ascii码为160的空格转换为html下的空格（&nbsp;）
     				   stringValue = stringValue.replaceAll(",", "");
     				   sb.append(stringValue.replace(String.valueOf((char) 160),"&nbsp;"));
     			   }
//     			   if(padAble != null && Arrays.asList(padAble).contains(tmp)){//生成pad展示数据string
//     				   padString+=stringValue+"@@";
//     			   }
     			   sb.append("</td>");
     		   }
     		   sb.append("</tr>");
     	   }
//     	   padString=padString.substring(0, padString.length()-2);
//     	   resultMap.put("padData", padString);
     	   
     	   sb.append("</table>");
     	   resultMap.put("computerData", sb.toString());
     	   return resultMap;
 		
 	} catch (Exception e) {
 		e.printStackTrace();
 		return null;
 	}
     }
     
    
    private Map<String, String>[] getRowSpanColSpanMap(Sheet sheet) {

        Map<String, String> map0 = new HashMap<String, String>();
        Map<String, String> map1 = new HashMap<String, String>();
        int mergedNum = sheet.getNumMergedRegions();
        CellRangeAddress range = null;
        for (int i = 0; i < mergedNum; i++) {
            range = sheet.getMergedRegion(i);
            int topRow = range.getFirstRow();
            int topCol = range.getFirstColumn();
            int bottomRow = range.getLastRow();
            int bottomCol = range.getLastColumn();
            map0.put(topRow + "," + topCol, bottomRow + "," + bottomCol);
            // System.out.println(topRow + "," + topCol + "," + bottomRow + "," + bottomCol);
            int tempRow = topRow;
            while (tempRow <= bottomRow) {
                int tempCol = topCol;
                while (tempCol <= bottomCol) {
                    map1.put(tempRow + "," + tempCol, "");
                    tempCol++;
                }
                tempRow++;
            }
            map1.remove(topRow + "," + topCol);
        }
        Map[] map = { map0, map1 };
        return map;
    }
    
    
    /**
     * 获取表格单元格Cell内容
     * @param cell
     * @return
     */
    public String getCellValue(Cell cell) {
	        String result = new String();  
	        switch (cell.getCellType()) {  
	        case Cell.CELL_TYPE_NUMERIC:// 数字类型  
	        case Cell.CELL_TYPE_FORMULA:
        		if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
        			SimpleDateFormat sdf = null;  
        			if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {  
        				sdf = new SimpleDateFormat("HH:mm");  
        			} else {// 日期  
        				sdf = new SimpleDateFormat("yyyy-MM-dd");  
        			}  
        			Date date = cell.getDateCellValue();  
        			result = sdf.format(date);  
        		} else if (cell.getCellStyle().getDataFormat() == 14 
        				|| cell.getCellStyle().getDataFormat() == 20
        				|| cell.getCellStyle().getDataFormat() == 31 
        				|| cell.getCellStyle().getDataFormat() == 32 
        				|| cell.getCellStyle().getDataFormat() == 57 
        				|| cell.getCellStyle().getDataFormat() == 58) {  
        			// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
        			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
        			double value = cell.getNumericCellValue();  
        			Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
        			result = sdf.format(date);  
        		} else {  
        			double value = cell.getNumericCellValue();  
        			CellStyle style = cell.getCellStyle();  
        			DecimalFormat format = new DecimalFormat();  
        			String temp = style.getDataFormatString();  
        			// 单元格设置成常规  
        			if (temp.equals("General")) {  
        				format.applyPattern("#");  
        			}  
        			result = format.format(value);  
        		}  
        		break;  
			
	        case Cell.CELL_TYPE_STRING:// String类型  
	            result = cell.getStringCellValue().toString(); 
	            break;  
	        case Cell.CELL_TYPE_BLANK:  
	            result = "";  
	            break; 
	        default:  
	            result = "";  
	            break;  
	        }  
	        return result;  
    }
    
    /**
     * 处理表格样式
     * @param wb
     * @param sheet
     * @param cell
     * @param sb
     * @throws Exception 
     */
    private void dealExcelStyle(Workbook wb,Sheet sheet,Cell cell,StringBuffer sb,boolean averageWidth) throws Exception{
        
        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle != null) {
            short alignment = cellStyle.getAlignment();
            sb.append("align='" + convertAlignToHtml(alignment) + "' ");//单元格内容的水平对齐方式
            short verticalAlignment = cellStyle.getVerticalAlignment();
            sb.append("valign='"+ convertVerticalAlignToHtml(verticalAlignment)+ "' ");//单元格中内容的垂直排列方式
            
            if (wb instanceof XSSFWorkbook) {
                            
                XSSFFont xf = ((XSSFCellStyle) cellStyle).getFont(); 
                short boldWeight = xf.getBoldweight();
                sb.append("style='");
                sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
                sb.append("font-size: " + xf.getFontHeight() / 2 + "%;"); // 字体大小
                if(averageWidth){
                	sb.append("width:" + 10 + "%;");
                }
                else{
                	int columnWidth = sheet.getColumnWidth(cell.getColumnIndex()) ;
                	sb.append("width:" + columnWidth + "px;");
                }
                
                XSSFColor xc = xf.getXSSFColor();
                if (xc != null && !"".equals(xc)) {
                    sb.append("color:#000000;"); // 字体颜色
                }
                
                XSSFColor bgColor = (XSSFColor) cellStyle.getFillForegroundColorColor();
                //System.out.println("BackgroundColorColor: "+cellStyle.getFillBackgroundColorColor());
                //System.out.println("ForegroundColor: "+cellStyle.getFillForegroundColor());//0
                //System.out.println("BackgroundColorColor: "+cellStyle.getFillBackgroundColorColor());
                //System.out.println("ForegroundColorColor: "+cellStyle.getFillForegroundColorColor());
                if (bgColor != null && !"".equals(bgColor)) {
                    sb.append("background-color:#C0C0C0;"); // 背景颜色
                }
                sb.append("border-top:solid #000000 1px;");
                sb.append("border-right:solid #000000 1px;");
                sb.append("border-bottom:solid #000000 1px;");
                sb.append("border-left:solid #000000 1px;");
                    
            }else if(wb instanceof HSSFWorkbook){
                
                HSSFFont hf = ((HSSFCellStyle) cellStyle).getFont(wb);
                short boldWeight = hf.getBoldweight();
                short fontColor = hf.getColor();
                sb.append("style='");
                HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette(); // 类HSSFPalette用于求的颜色的国际标准形式
                HSSFColor hc = palette.getColor(fontColor);
                sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
                sb.append("font-size: " + hf.getFontHeight() / 2 + "%;"); // 字体大小
                String fontColorStr = convertToStardColor(hc);
                if (fontColorStr != null && !"".equals(fontColorStr.trim())) {
                    sb.append("color:" + fontColorStr + ";"); // 字体颜色
                }
                
                if(averageWidth){
                	sb.append("width:" + 10 + "%;");
                }
                else{
                	int columnWidth = sheet.getColumnWidth(cell.getColumnIndex()) ;
                	sb.append("width:" + columnWidth + "px;");
                }
                short bgColor = cellStyle.getFillForegroundColor();
                hc = palette.getColor(bgColor);
                String bgColorStr = convertToStardColor(hc);
                if (bgColorStr != null && !"".equals(bgColorStr.trim())) {
                    sb.append("background-color:" + bgColorStr + ";"); // 背景颜色
                }
                sb.append( getBorderStyle(palette,0,cellStyle.getBorderTop(),cellStyle.getTopBorderColor()));
                sb.append( getBorderStyle(palette,1,cellStyle.getBorderRight(),cellStyle.getRightBorderColor()));
                sb.append( getBorderStyle(palette,3,cellStyle.getBorderLeft(),cellStyle.getLeftBorderColor()));
                sb.append( getBorderStyle(palette,2,cellStyle.getBorderBottom(),cellStyle.getBottomBorderColor()));
            }

            sb.append("' ");
        }
    }
    
    /**
     * 单元格内容的水平对齐方式
     * @param alignment
     * @return
     */
    private String convertAlignToHtml(short alignment) {

        String align = "left";
        switch (alignment) {
        case CellStyle.ALIGN_LEFT:
            align = "left";
            break;
        case CellStyle.ALIGN_CENTER:
            align = "center";
            break;
        case CellStyle.ALIGN_RIGHT:
            align = "right";
            break;
        default:
            break;
        }
        return align;
    }

    /**
     * 单元格中内容的垂直排列方式
     * @param verticalAlignment
     * @return
     */
    private String convertVerticalAlignToHtml(short verticalAlignment) {

        String valign = "middle";
        switch (verticalAlignment) {
        case CellStyle.VERTICAL_BOTTOM:
            valign = "bottom";
            break;
        case CellStyle.VERTICAL_CENTER:
            valign = "center";
            break;
        case CellStyle.VERTICAL_TOP:
            valign = "top";
            break;
        default:
            break;
        }
        return valign;
    }
    
    private String convertToStardColor(HSSFColor hc) {

        StringBuffer sb = new StringBuffer("");
        if (hc != null) {
            if (HSSFColor.AUTOMATIC.index == hc.getIndex()) {
                return null;
            }
            sb.append("#");
            for (int i = 0; i < hc.getTriplet().length; i++) {
                sb.append(fillWithZero(Integer.toHexString(hc.getTriplet()[i])));
            }
        }

        return sb.toString();
    }
    
    private String fillWithZero(String str) {
        if (str != null && str.length() < 2) {
            return "0" + str;
        }
        return str;
    }
    
    static String[] bordesr={"border-top:","border-right:","border-bottom:","border-left:"};
    static String[] borderStyles={"solid ","solid ","solid ","solid ","solid ","solid ","solid ","solid ","solid ","solid","solid","solid","solid","solid"};

    private String getBorderStyle(  HSSFPalette palette ,int b,short s, short t){
         
        if(s==0)return  bordesr[b]+borderStyles[s]+"#d0d7e5 1px;";;
        String borderColorStr = convertToStardColor( palette.getColor(t));
        borderColorStr=borderColorStr==null|| borderColorStr.length()<1?"#000000":borderColorStr;
        return bordesr[b]+borderStyles[s]+borderColorStr+" 1px;";
        
    }
    
    private String getBorderStyle(int b,short s, XSSFColor xc){
         
         if(s==0)return  bordesr[b]+borderStyles[s]+"#d0d7e5 1px;";;
         if (xc != null && !"".equals(xc)) {
             String borderColorStr = xc.getARGBHex();//t.getARGBHex();
             borderColorStr=borderColorStr==null|| borderColorStr.length()<1?"#000000":borderColorStr.substring(2);
             return bordesr[b]+borderStyles[s]+borderColorStr+" 1px;";
         }
         
         return "";
    }
    
    public static String getBASE64(String s) { 
    	if (s == null) return null; 
    	return (new BASE64Encoder()).encode( s.getBytes() ); 
	} 

    private static int columnToIndex(String column) {
        if (!column.matches("[A-Z]+")) {
                try {
                        throw new Exception("Invalid parameter");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        int index = 0;
        char[] chars = column.toUpperCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
                index += ((int) chars[i] - (int) 'A' + 1)
                                * (int) Math.pow(26, chars.length - i - 1);
        }
        return index;
    }
    
    private static String indexToColumn(int index) throws Exception {
        if (index <= 0) {                 
        		throw new Exception("Invalid parameter");         
        	}         
        index--;         
        String column = "";         
        do {                 
        	if (column.length() > 0) {
                        index--;
                }
                column = ((char) (index % 26 + (int) 'A')) + column;
                index = (int) ((index - index % 26) / 26);
        } while (index > 0);
        return column;
    }

    
    
  public String[] readExcelToHtml1(String filePath, boolean isWithStyle){
      
  	String sheet[] = new String[19];
      InputStream is = null;
      String approveValue="";
      Map<String, String> map = new HashMap<String, String>();
      try {
          File sourcefile = new File(filePath);
          is = new FileInputStream(sourcefile);
          Workbook wb = WorkbookFactory.create(is);
          for(int i=0;i<wb.getNumberOfSheets();i++)
          {
          	System.out.println(wb.getSheetAt(i).getSheetName());
          	if(wb.getSheetAt(i).getSheetName().indexOf("建议")>=0){
          		if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      //取申请金额（第三行第四列）
                      Sheet st = wb.getSheetAt(0);
                      Row row = st.getRow(2);
                      Cell cell = row.getCell(4);
                      approveValue = getCellValue(cell);
                      CustomerSqInfo.setSqje(approveValue);
                      //客户名称
                      Row name = st.getRow(2);
                      Cell namecell = name.getCell(1);
                      CustomerSqInfo.setName(getCellValue(namecell));
                      
                      //贷款用途
                      Row dkyt = st.getRow(2);
                      Cell dkytcell = dkyt.getCell(6);
                      CustomerSqInfo.setDkyt(getCellValue(dkytcell));
                      //申请期限
                      Row sqqx = st.getRow(3);
                      
                      Cell sqqxcell = sqqx.getCell(1);
                      CustomerSqInfo.setSqqx(getCellValue(sqqxcell));
                      
                      
                      //建议金额
                      Row jyje = st.getRow(9);
                      Cell jyjecell = jyje.getCell(3);
                      CustomerSqInfo.setJyje(getCellValue(jyjecell));
                      
                      
                      //建议期限
                      Row jyqx = st.getRow(9);
                      Cell jyqxcell = jyqx.getCell(5);
                      CustomerSqInfo.setJyqx(getCellValue(jyqxcell));
                      
                      //适用产品
                      Row jycp = st.getRow(9);
                      Cell jycpcell = jycp.getCell(7);
                      CustomerSqInfo.setJycp(getCellValue(jycpcell));
                      
                      
                      //建议利率
                      Row jylv = st.getRow(9);
                      Cell jylvcell = jylv.getCell(9);
                      CustomerSqInfo.setJylv(getCellValue(jylvcell));
                      
                      
                      //建议担保人
                      Row jydbr = st.getRow(11);
                      Cell jydbrcell = jydbr.getCell(3);
                      CustomerSqInfo.setJydbr(getCellValue(jydbrcell));
                      
                    //与担保人关系
                      Row gx = st.getRow(11);
                      Cell gxcell = gx.getCell(5);
                      CustomerSqInfo.setGx(getCellValue(gxcell));
                      
                      
                      //建议抵押物
                      Row dyw = st.getRow(11);
                      Cell dywcell = dyw.getCell(7);
                      CustomerSqInfo.setJydyw(getCellValue(dywcell));
                      
                      //抵押物归属
                      Row dywgs = st.getRow(11);
                      Cell dywgscell = dywgs.getCell(9);
                      CustomerSqInfo.setWqr(getCellValue(dywgscell));
                      //证件号码
                      Row cardId = st.getRow(14);
                      Cell cardIdcell = cardId.getCell(6);
                      CustomerSqInfo.setCardId(getCellValue(cardIdcell));
                      
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jy,ImportParameter.editAble_jy,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                    //取申请金额（第三行第四列）
                      Sheet st = wb.getSheetAt(0);
                      Row row = st.getRow(2);
                      Cell cell = row.getCell(4);
                      approveValue = getCellValue(cell);
                      CustomerSqInfo.setSqje(approveValue);
                      //客户名称
                      Row name = st.getRow(2);
                      Cell namecell = name.getCell(1);
                      CustomerSqInfo.setName(getCellValue(namecell));
                      //证件号码
                      Row cardId = st.getRow(14);
                      Cell cardIdcell = cardId.getCell(6);
                      CustomerSqInfo.setCardId(getCellValue(cardIdcell));
                      
                      //贷款用途
                      Row dkyt = st.getRow(2);
                      Cell dkytcell = dkyt.getCell(6);
                      CustomerSqInfo.setDkyt(getCellValue(dkytcell));
                      
                      //申请期限
                      Row sqqx = st.getRow(3);
                      Cell sqqxcell = sqqx.getCell(1);
                      CustomerSqInfo.setSqqx(getCellValue(sqqxcell));
                      
                      
                      //建议金额
                      Row jyje = st.getRow(9);
                      Cell jyjecell = jyje.getCell(3);
                      CustomerSqInfo.setJyje(getCellValue(jyjecell));
                      
                      
                      //建议期限
                      Row jyqx = st.getRow(9);
                      Cell jyqxcell = jyqx.getCell(5);
                      CustomerSqInfo.setJyqx(getCellValue(jyqxcell));
                      
                      //适用产品
                      Row jycp = st.getRow(9);
                      Cell jycpcell = jycp.getCell(7);
                      CustomerSqInfo.setJycp(getCellValue(jycpcell));
                      
                      
                      //建议利率
                      Row jylv = st.getRow(9);
                      Cell jylvcell = jylv.getCell(9);
                      CustomerSqInfo.setJylv(getCellValue(jylvcell));
                      
                      
                      //建议担保人
                      Row jydbr = st.getRow(11);
                      Cell jydbrcell = jydbr.getCell(3);
                      CustomerSqInfo.setJydbr(getCellValue(jydbrcell));
                      
                    //与担保人关系
                      Row gx = st.getRow(11);
                      Cell gxcell = gx.getCell(5);
                      CustomerSqInfo.setGx(getCellValue(gxcell));
                      
                      
                      //建议抵押物
                      Row dyw = st.getRow(11);
                      Cell dywcell = dyw.getCell(7);
                      CustomerSqInfo.setJydyw(getCellValue(dywcell));
                      
                      //抵押物归属
                      Row dywgs = st.getRow(11);
                      Cell dywgscell = dywgs.getCell(9);
                      CustomerSqInfo.setWqr(getCellValue(dywgscell));
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jy,ImportParameter.editAble_jy,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
          		sheet[0] = content_base64;
          	}
          	if(wb.getSheetAt(i).getSheetName().indexOf("基本状况")>=0){
          		if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jbzk,ImportParameter.editAble_jbzk,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jbzk,ImportParameter.editAble_jbzk,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
          		sheet[1] = content_base64;
          	}
          	
          	if(wb.getSheetAt(i).getSheetName().indexOf("经营状态")>=0){
          		if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jyzk,ImportParameter.editAble_jyzk,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jyzk,ImportParameter.editAble_jyzk,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
          		sheet[2] = content_base64;
          	}
          	
          	if(wb.getSheetAt(i).getSheetName().indexOf("生存状态")>=0){
          		if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_sczt,ImportParameter.editAble_sczt,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_sczt,ImportParameter.editAble_sczt,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
          		sheet[3] = content_base64;
          	}
          	
          	if(wb.getSheetAt(i).getSheetName().indexOf("道德品质")>=0){
          		if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ddpz,ImportParameter.editAble_ddpz,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ddpz,ImportParameter.editAble_ddpz,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
          		sheet[4] = content_base64;
          	}
          	
          	if(wb.getSheetAt(i).getSheetName().indexOf("资产负债")>=0){
          		if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_zf,ImportParameter.editAble_fz,true);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_zf,ImportParameter.editAble_fz,true);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
          		sheet[5] = content_base64;
          	}
          	else if(wb.getSheetAt(i).getSheetName().indexOf("利润简表")>=0){
          		if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_lrjb,ImportParameter.editAble_lrjb,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_lrjb,ImportParameter.editAble_lrjb,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
          		sheet[6] = content_base64;
          	}
				else if(wb.getSheetAt(i).getSheetName().indexOf("标准利润表")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_bzlrb,ImportParameter.editAble_bzlrb,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_bzlrb,ImportParameter.editAble_bzlrb,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[7] = content_base64;
          	}
				else if(wb.getSheetAt(i).getSheetName().indexOf("主营业务明细表")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_zyyw,ImportParameter.editAble_zyyw,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_zyyw,ImportParameter.editAble_zyyw,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[8] = content_base64;
          	}
				else if(wb.getSheetAt(i).getSheetName().indexOf("现金流量表")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_xjllb,ImportParameter.editAble_xjllb,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_xjllb,ImportParameter.editAble_xjllb,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[9] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("交叉检验")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jc,ImportParameter.editAble_jc,true);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jc,ImportParameter.editAble_jc,true);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[10] = content_base64;
				}
          	
				else if(wb.getSheetAt(i).getSheetName().indexOf("点货单")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_dhd,ImportParameter.editAble_dhd,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_dhd,ImportParameter.editAble_dhd,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[11] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("固定资产")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_gdzc,ImportParameter.editAble_gdzc,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_gdzc,ImportParameter.editAble_gdzc,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[12] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("应付预收")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_yfys,ImportParameter.editAble_yfys,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_yfys,ImportParameter.editAble_yfys,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[13] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("应收预付")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ysyf,ImportParameter.editAble_ysyf,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ysyf,ImportParameter.editAble_ysyf,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[14] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("流水分析")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_lsfx,ImportParameter.editAble_lsfx,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_lsfx,ImportParameter.editAble_lsfx,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[15] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("决议表")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jyb,ImportParameter.editAble_jyb,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jyb,ImportParameter.editAble_jyb,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[16] = content_base64;
				}else if(wb.getSheetAt(i).getSheetName().indexOf("保证人信息表")>=0){
					if (wb instanceof XSSFWorkbook) {
                      XSSFWorkbook xWb = (XSSFWorkbook) wb;
                      map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_dbrxxb,ImportParameter.editAble_dbrxxb,false);
                  }else if(wb instanceof HSSFWorkbook){
                      HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_dbrxxb,ImportParameter.editAble_dbrxxb,false);
                  }
              	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[17] = content_base64;
				}
          	sheet[18] = approveValue;
          }
          
      } catch (Exception e) {
          e.printStackTrace();
      }finally{
          try {
              is.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      return sheet;
  /*
        
    	String sheet[] = new String[13];
        InputStream is = null;
        String approveValue="";
//        String htmlExcel = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            File sourcefile = new File(filePath);
            is = new FileInputStream(sourcefile);
            Workbook wb = WorkbookFactory.create(is);
            for(int i=0;i<wb.getNumberOfSheets();i++)
            {
            	//System.out.println(wb.getSheetAt(i).getSheetName());
            	if(wb.getSheetAt(i).getSheetName().indexOf("建议")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(0);
                        Row row = st.getRow(2);
                        Cell cell = row.getCell(4);
                        approveValue = getCellValue(cell);
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jy,ImportParameter.editAble_jy,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                      //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(0);
                        Row row = st.getRow(2);
                        Cell cell = row.getCell(4);
                        approveValue = getCellValue(cell);
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jy,ImportParameter.editAble_jy,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[0] = content_base64;
            	}
            	if(wb.getSheetAt(i).getSheetName().indexOf("基本状况")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jbzk,ImportParameter.editAble_jbzk,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jbzk,ImportParameter.editAble_jbzk,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[1] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("经营状态")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jyzk,ImportParameter.editAble_jyzk,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jyzk,ImportParameter.editAble_jyzk,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[2] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("生存状态")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_sczt,ImportParameter.editAble_sczt,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_sczt,ImportParameter.editAble_sczt,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[3] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("道德品质")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ddpz,ImportParameter.editAble_ddpz,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ddpz,ImportParameter.editAble_ddpz,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[4] = content_base64;
            	}
            	
            	if(wb.getSheetAt(i).getSheetName().indexOf("资产负债")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_zf,ImportParameter.editAble_fz,true);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_zf,ImportParameter.editAble_fz,true);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[2] = content_base64;
            	}
            	else if(wb.getSheetAt(i).getSheetName().indexOf("利润简表")>=0){
            		if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_lrjb,ImportParameter.editAble_lrjb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_lrjb,ImportParameter.editAble_lrjb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
            		sheet[6] = content_base64;
            	}
				else if(wb.getSheetAt(i).getSheetName().indexOf("标准利润表")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_bzlrb,ImportParameter.editAble_bzlrb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_bzlrb,ImportParameter.editAble_bzlrb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[3] = content_base64;
            	}
				else if(wb.getSheetAt(i).getSheetName().indexOf("主营业务明细表")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_zyyw,ImportParameter.editAble_zyyw,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_zyyw,ImportParameter.editAble_zyyw,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[8] = content_base64;
            	}
				else if(wb.getSheetAt(i).getSheetName().indexOf("现金流量表")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_xjllb,ImportParameter.editAble_xjllb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_xjllb,ImportParameter.editAble_xjllb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[4] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("交叉检验")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jc,ImportParameter.editAble_jc,true);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jc,ImportParameter.editAble_jc,true);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[5] = content_base64;
				}
            	
				else if(wb.getSheetAt(i).getSheetName().indexOf("点货单")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_dhd,ImportParameter.editAble_dhd,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_dhd,ImportParameter.editAble_dhd,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[11] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("固定资产")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_gdzc,ImportParameter.editAble_gdzc,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_gdzc,ImportParameter.editAble_gdzc,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[6] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("应付预收")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_yfys,ImportParameter.editAble_yfys,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_yfys,ImportParameter.editAble_yfys,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[7] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("应收预付")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ysyf,ImportParameter.editAble_ysyf,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ysyf,ImportParameter.editAble_ysyf,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[8] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("流水分析")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_lsfx,ImportParameter.editAble_lsfx,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_lsfx,ImportParameter.editAble_lsfx,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[15] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("决议表")>=0){
					if (wb instanceof XSSFWorkbook) {
                        XSSFWorkbook xWb = (XSSFWorkbook) wb;
                        map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_jyb,ImportParameter.editAble_jyb,false);
                    }else if(wb instanceof HSSFWorkbook){
                        HSSFWorkbook hWb = (HSSFWorkbook) wb;
                        map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_jyb,ImportParameter.editAble_jyb,false);
                    }
                	String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[9] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("抵贷通经营表")>=0){
					if (wb instanceof XSSFWorkbook) {
						XSSFWorkbook xWb = (XSSFWorkbook) wb;
						 //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(10);
                        Row row = st.getRow(4);
                        Cell cell = row.getCell(1);
                        if(getCellValue(cell)!=null&&getCellValue(cell)!=""){
                        	approveValue = getCellValue(cell);
                        }
						map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ddtscjy,ImportParameter.editAble_ddtscjy,false);
					}else if(wb instanceof HSSFWorkbook){
						 //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(10);
                        Row row = st.getRow(4);
                        Cell cell = row.getCell(1);
                        if(getCellValue(cell)!=null&&getCellValue(cell)!=""){
                        	approveValue = getCellValue(cell);
                        }
						HSSFWorkbook hWb = (HSSFWorkbook) wb;
						map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ddtscjy,ImportParameter.editAble_ddtscjy,false);
					}
					String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[10] = content_base64;
				}
				else if(wb.getSheetAt(i).getSheetName().indexOf("抵贷通消费表")>=0){
					if (wb instanceof XSSFWorkbook) {
						XSSFWorkbook xWb = (XSSFWorkbook) wb;
						 //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(11);
                        Row row = st.getRow(4);
                        Cell cell = row.getCell(1);
                        if(getCellValue(cell)!=null&&getCellValue(cell)!=""){
                        	approveValue = getCellValue(cell);
                        }
						map = getExcelInfo(xWb,i,isWithStyle,ImportParameter.RowAndCol_ddtgrxf,ImportParameter.editAble_ddtgrxf,false);
					}else if(wb instanceof HSSFWorkbook){
						HSSFWorkbook hWb = (HSSFWorkbook) wb;
						 //取申请金额（第三行第四列）
                        Sheet st = wb.getSheetAt(11);
                        Row row = st.getRow(4);
                        Cell cell = row.getCell(1);
                        if(getCellValue(cell)!=null&&getCellValue(cell)!=""){
                        	approveValue = getCellValue(cell);
                        }
						map = getExcelInfo(hWb,i,isWithStyle,ImportParameter.RowAndCol_ddtgrxf,ImportParameter.editAble_ddtgrxf,false);
					}
					String content_base64 = getBASE64(map.get("computerData").toString());
					sheet[11] = content_base64;
				}
            	sheet[12] = approveValue;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sheet;
    */}
}