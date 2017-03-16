package cn.wenqi.oauth2.sparklr.service;

import cn.wenqi.oauth2.sparklr.model.PhotoInfo;

import java.io.InputStream;
import java.util.Collection;

/**
 * @author wenqi
 */
public interface PhotoService {
    /**
     * Load the photos for the current user.
     *
     * @return The photos for the current user.
     */
    Collection<PhotoInfo> getPhotosForCurrentUser(String username);

    /**
     * Load a photo by id.
     *
     * @param id The id of the photo.
     * @return The photo that was read.
     */
    InputStream loadPhoto(String id);
}
