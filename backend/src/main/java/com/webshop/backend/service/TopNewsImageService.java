package com.webshop.backend.service;

import com.webshop.backend.dto.TopNewsImageDTO;
import com.webshop.backend.fileserrvice.FileService;
import com.webshop.backend.model.TopNewsImage;
import com.webshop.backend.repository.TopNewsImageRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopNewsImageService {
    @Value("${photo.top.news.directory}")
    private String topNewsImageDirectory;

    @Autowired
    private TopNewsImageRepository topNewsImageRepository;

    @Autowired
    private FileService fileService;

    private static final boolean IS_ACTIVE_DEFAULT = true;
    private static final boolean IS_DELETED_DEFAULT = false;

    public TopNewsImage saveTopNewsImage(MultipartFile imageFile) throws IOException {

        String savedImagePath = fileService.saveImageIntoDirectory(imageFile, topNewsImageDirectory);
        TopNewsImage topNewsImage = new TopNewsImage();
        topNewsImage.setFilePath(savedImagePath);
        topNewsImage.setActive(IS_ACTIVE_DEFAULT);
        topNewsImage.setDeleted(IS_DELETED_DEFAULT);
        return topNewsImageRepository.save(topNewsImage);
    }

    public boolean saveMultipleTopNewsImage(MultipartFile[] imageFiles) throws IOException {
        for (MultipartFile imageFile: imageFiles) {
            saveTopNewsImage(imageFile);
        }

        return true;
    }

    public List<TopNewsImageDTO> getTopNewsImage() throws IOException {
        List<TopNewsImage> topNewsImageList = topNewsImageRepository.findByIsActiveTrueAndIsDeletedFalse();
        List<TopNewsImageDTO> topNewsImageDTOList = new ArrayList<>();

        for (TopNewsImage topNewsImage: topNewsImageList) {
            TopNewsImageDTO dto = new TopNewsImageDTO();
            dto.setId(topNewsImage.getId());
            dto.setFilePath(topNewsImage.getFilePath());

            File file = new File(topNewsImage.getFilePath());

            dto.setContent(FileUtils.readFileToByteArray(file));

            topNewsImageDTOList.add(dto);
        }

        return topNewsImageDTOList;

        //Mivel édesanyám egyedül nevelt és nevel minket hármunkat, emiatt anyagilag nem volt lehetőségünk még rá, de nem mondtunk le róla
        //
        //Első perctől voltak konfliktusok a nézeteltérések, összeszólalkozások amit egy darabig tűrtek és egy idő után már nem tűrtek, a vallás miatt voltak megszólítások, anyáskodni akart az egyik személy felett, nem ittam, nem szivaraztam, túl sokat megyek vallási helyekre,
        // a munka utáni csapattréningek, amikkel elvették
        //Keresték, hogy más módszerrel csinálják meg, de voltak olyanok, hogy végül mégis az az út lett, amit én választottam, de azt nem ismerték el

        //Ők csak az én hibáimra hivatkoztak, a gyenge pontjaimra utalva kiraktak engemet, de
        //Ők csak arra hivatkoztak,... de nem fogadtam el eztet, mert voltak olyan munkák, amiket ki tudtak kerülni, hogy ne az én módszeremmel csinálják meg, de volt olyan is, amiket nem tudtak kikerülni,
        //  és nekem lett igazam, de azt meg sem említették, szóvá se tették. Illetve volt olyan nagyobb projekt is, aminél azt hittem, hogy nem fogom tudni megcsinálni, végül mégis, magamat is meglepve, de meg
        //  tudtam csinálni
        // A háttérben más problémák voltak, erre akartam utalni

        //atruhazom, atrakom, athelyezem
        //kik mit vetettek az eletunkben, azt arassak le
        //ki masnak vermet as, azok essenek bele
    }
}
