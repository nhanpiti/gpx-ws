package com.nhanpiti.gpx.ws;

import com.nhanpiti.gpx.ws.service.FileSystemStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author nhanpiti
 */

@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileSystemStorageService fileSystemStorageService;

    @Test
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "data/sample.gpx",
                "text/plain", "Spring Framework".getBytes());

        this.mvc.perform(multipart("/gpx/upload/").file(multipartFile))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/gpx/upload"));

        then(this.fileSystemStorageService).should().store(multipartFile);
    }
}
