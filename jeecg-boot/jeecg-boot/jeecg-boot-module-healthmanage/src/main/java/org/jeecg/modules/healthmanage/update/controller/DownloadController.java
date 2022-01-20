package org.jeecg.modules.healthmanage.update.controller;
//

//import java.util.List;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.healthmanage.measure.utils.YsylException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
//import com.ysyl.service.IUserService;
@Api(tags="下载指定版本APK")
@RestController
@RequestMapping(value = "/download", produces = "application/x-msdownload")
public class DownloadController {

	private static transient Logger logger = LoggerFactory.getLogger(DownloadController.class);
	@Value("${app.downloadpath}")
	String appDownloadPath;


//	app:   /download/androidapp
//	download:
//	path: /Users/kkpyqtjhucn/service_rxble/ysyl-service/static/
//	fileName: yn.apk
//	jsonName: abc.json
//	versionCode: 2
//	versionName: 1.5.6
//	content: "1.增加按测量结果降序显示"
//	MinSupport: 1
	
//	  参数丢后缀，找不到文件
	@RequestMapping(value = "/androidapp/{appDownloadName}", method = RequestMethod.GET)
	public void Download(@PathVariable(value="appDownloadName") String appDownloadNameIn, HttpServletResponse res) {
        String appDownloadName= appDownloadNameIn  ;
//        String appDownloadName= appDownloadNameIn+".apk"  ;
//		 logger.info("获取到入参"+appDownloadName);
		if (StringUtils.isAnyEmpty(appDownloadPath, appDownloadName )) {
			throw new YsylException("file not empty");
		}
//		profile 指定下载文件夹+ 版本管理器中获取的文件名称
		File downloadFile = new File(appDownloadPath+appDownloadName );  

		res.setHeader("content-type", "application/octet-stream");
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment;filename=" + appDownloadName );
		res.setContentLength((int) downloadFile.length()); 
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File(appDownloadPath + appDownloadName )));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("success");
	}


}