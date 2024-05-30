package com.joel.ocb

import grails.util.Holders
import org.springframework.web.multipart.MultipartFile
import java.io.File

class FileUtil {
    public static String getRootPath() {
        String rootPath = Holders.servletContext?.getRealPath("")

    }



    public static File makeDirectory(String path){
        File file = new File(path)
        if (!file.exists()){
            file.mkdirs()
        }
        return file
    }

    //    request.getFile("productFile")
    public static String uploadContactImage(Integer contactId, MultipartFile multipartFile){
        if (contactId && multipartFile){
            String contactImagePath = "${getRootPath()}contact-image/"
            makeDirectory(contactImagePath)
            multipartFile.transferTo(new File(contactImagePath, contactId + "-" + multipartFile.originalFilename))
            return multipartFile.originalFilename
        }
        return ""
    }

    public static Boolean deleteContactImage(String imageName, Integer id) {
        if (imageName) {
            String newImageName = id + "-" + imageName
            String contactImagePath = "${getRootPath()}contact-image/"+ newImageName
            File imageFile = new File(contactImagePath)
            println("caminho da imagem: ${imageFile.path}")
            if (imageFile.exists()) {
               println("se existir delete")
                boolean deleted = imageFile.delete()
                if (deleted) {
                    println("Imagem apagada com Sucesso")
                } else {
                    println("falha ao deletar imagem")
                }
                return deleted
            } else {
                println("Arquivo de imagem não existe")
            }
        } else {
            println("nome da imagem invalido")
        }
        return false
    }
}
