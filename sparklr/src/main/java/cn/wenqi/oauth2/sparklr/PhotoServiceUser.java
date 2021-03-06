package cn.wenqi.oauth2.sparklr;

/**
 * @author wenqi
 */
public class PhotoServiceUser {
    private String username;
    private String name;

    /**
     * Create a new PhotoServiceUser
     *
     * @param username The unique username for the user
     * @param name The name of the user
     */
    public PhotoServiceUser(String username,String name)
    {
        this.username = username;
        this.name = name;
    }

    /**
     * The unique username for the user
     *
     * @return username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * The name of the user
     *
     * @return name of the user
     */
    public String getName() {
        return name;
    }
}
