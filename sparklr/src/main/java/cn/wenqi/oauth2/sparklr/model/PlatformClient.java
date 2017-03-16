package cn.wenqi.oauth2.sparklr.model;

import cn.wenqi.oauth2.sparklr.util.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author wenqi
 */

@Setter
@Getter
@Entity
@Table(name = "Platform_Client",uniqueConstraints = {@UniqueConstraint(columnNames = "clientId")})
public class PlatformClient implements ClientDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cid;

    @JsonIgnore
    @CollectionTable(name = "PlatformClient_resIds")
    @ElementCollection
    @Column(length = 50)
    private Set<String> resIds;

    private boolean secretRequired;

    private String clientSecret;

    private boolean scoped;

    @JsonIgnore
    @CollectionTable(name = "PlatformClient_scope")
    @ElementCollection
    @Column(length = 50)
    private Set<String> scope;


    @JsonIgnore
    @CollectionTable(name = "PlatformClient_authorizedGrantTypes")
    @ElementCollection
    @Column(length = 50)
    private Set<String> authorizedGrantTypes;


    @JsonIgnore
    @CollectionTable(name = "PlatformClient_registeredRedirectUri")
    @ElementCollection
    @Column(length = 50)
    private Set<String> registeredRedirectUri;


    @JsonIgnore
    @CollectionTable(name = "PlatformClient_grantedAuthorities")
    @ElementCollection
    @Column(length = 50)
    private Set<String> grantedAuthorities;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private boolean autoApprove;

    private Map<String, Object> additionalInformation;


    public String getClientId() {
        return cid;
    }

    public Set<String> getResourceIds() {
        return resIds;
    }

    public boolean isSecretRequired() {
        return secretRequired;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public boolean isScoped() {
        return scoped;
    }

    public Set<String> getScope() {
        return scope;
    }

    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        if (grantedAuthorities == null || grantedAuthorities.isEmpty())
            return Utils.String2GrantedAuthoritySet(Collections.singletonList("ROLE_USER").stream());
        if (!grantedAuthorities.contains("ROLE_USER"))
            grantedAuthorities.add("ROLE_USER");
        return Utils.String2GrantedAuthoritySet(grantedAuthorities.stream());
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }
}
