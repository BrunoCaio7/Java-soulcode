package com.soulcode.servicos.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadFile {

    public  static void saveFile(String upLoadDir, String fileName, MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(upLoadDir);

        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        // aqui vamos tentar fazer o upload do arquivo
        //InputStream - tenta fazer a leitura do arquivo que estamos querendo subir
        // faz a leitura byte por byte do arquivo
        try(InputStream inputStream = file.getInputStream()){


        // nesse momento o arquivo é salvo no diretório que passamos na assinatura do método
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e){
            throw new IOException("Não foi possível enviar o seu arquivo");
        }
    }
}
