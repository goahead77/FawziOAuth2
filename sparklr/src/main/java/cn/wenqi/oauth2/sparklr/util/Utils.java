package cn.wenqi.oauth2.sparklr.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wenqi
 */
public class Utils {

    public static Set<GrantedAuthority> String2GrantedAuthoritySet(Stream<String> input) {
        return input.map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
