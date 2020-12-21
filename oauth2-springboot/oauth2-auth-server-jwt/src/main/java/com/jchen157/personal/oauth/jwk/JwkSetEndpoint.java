package com.jchen157.personal.oauth.jwk;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@FrameworkEndpoint
public class JwkSetEndpoint {

  @GetMapping("/.well-known/jwks.json")
  @ResponseBody
  public Map<String, Object> getKey(Principal principal) throws Exception {
    RSAPublicKey publicKey = (RSAPublicKey) keyPair().getPublic();
    RSAKey key = new RSAKey.Builder(publicKey).build();
    return new JWKSet(key).toJSONObject();
  }

  public KeyPair keyPair() throws Exception {
    InputStream is =
            this.getClass().getClassLoader().getResourceAsStream("my-release-key.keystore");

    KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
    keystore.load(is, "jj950303".toCharArray());
    String alias = "alias_name";

    Key key = keystore.getKey(alias, "jj950303".toCharArray());

    if (key instanceof PrivateKey) {
      // Get certificate of public key
      Certificate cert = keystore.getCertificate(alias);

      // Get public key
      PublicKey publicKey = cert.getPublicKey();

      // Return a key pair
      return new KeyPair(publicKey, (PrivateKey) key);
    } else {
      return null;
    }
  }
}
