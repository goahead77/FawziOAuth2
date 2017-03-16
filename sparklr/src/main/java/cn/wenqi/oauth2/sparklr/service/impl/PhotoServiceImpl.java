package cn.wenqi.oauth2.sparklr.service.impl;

import cn.wenqi.oauth2.sparklr.model.PhotoInfo;
import cn.wenqi.oauth2.sparklr.service.PhotoService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author wenqi
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    private List<PhotoInfo> photos;

    private PhotoInfo createPhoto(String id, String userId) {
        PhotoInfo photo = new PhotoInfo();
        photo.setId(id);
        photo.setName("photo" + id + ".jpg");
        photo.setUserId(userId);
        photo.setResourceURL("/imgs/" + photo.getName());
        return photo;
    }

    public Collection<PhotoInfo> getPhotosForCurrentUser(String username) {

        ArrayList<PhotoInfo> infos = new ArrayList<PhotoInfo>();
        for (PhotoInfo info : getPhotos()) {
            if (username.equals(info.getUserId())) {
                infos.add(info);
            }
        }
        return infos;

    }

    public InputStream loadPhoto(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails details = (UserDetails) authentication.getPrincipal();
            String username = details.getUsername();
            for (PhotoInfo photoInfo : getPhotos()) {
                if (id.equals(photoInfo.getId()) && username.equals(photoInfo.getUserId())) {
                    URL resourceURL = getClass().getResource(photoInfo.getResourceURL());
                    if (resourceURL != null) {
                        try {
                            return resourceURL.openStream();
                        } catch (IOException e) {
                            // fall through...
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<PhotoInfo> getPhotos() {
        photos = new ArrayList<PhotoInfo>();
        photos.add(createPhoto("1", "marissa"));
        photos.add(createPhoto("2", "paul"));
        photos.add(createPhoto("3", "marissa"));
        photos.add(createPhoto("4", "paul"));
        photos.add(createPhoto("5", "marissa"));
        photos.add(createPhoto("6", "paul"));
        return photos;
    }

}
