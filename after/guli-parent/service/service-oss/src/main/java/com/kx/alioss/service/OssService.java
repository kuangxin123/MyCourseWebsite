package com.kx.alioss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String upFile(MultipartFile file);
}
