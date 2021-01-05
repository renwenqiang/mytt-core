package com.boot.mytt.core.controller;
 
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
 
@Controller
public class FileUploadController {
	
	//文件下载
	@RequestMapping(value="/fileDownload")
	public ResponseEntity<byte[]> fileDownload(HttpServletRequest request,
			@RequestParam("fileName") String fileName,Model model) throws Exception {
		//下载文件路径
		String path=request.getServletContext().getRealPath("/upload/");
		//创建文件对象
		File file=new File(path+File.separator+fileName);
		//设置相应头
		HttpHeaders headers=new HttpHeaders();
		//下载显示的文件名，解决中文名乱码问题
		String downloadFileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		//通知浏览器以下载方式（attachment）打开文件
		headers.setContentDispositionFormData("attachment", downloadFileName);
		//定义以二进制流的形式下载返回的文件数据
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//使用Spring MVC框架的ResponseEntity对象封装返回下载的数据
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
		
	}
	
}