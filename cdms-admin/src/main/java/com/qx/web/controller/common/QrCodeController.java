package com.qx.web.controller.common;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qx.common.utils.QRCodeUtil;

/**
 * 
 * @author suhp
 *
 */
@RestController
public class QrCodeController {
	
	/**
     * 根据 url 生成 普通二维码
     */
	@GetMapping("common/download/qrCode")
    public void createCommonQRCode(HttpServletResponse response, String url) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //使用工具类生成二维码
            QRCodeUtil.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
	
	
}
