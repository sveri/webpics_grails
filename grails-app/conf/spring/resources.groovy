// Place your Spring DSL code here
import org.apache.shiro.authc.credential.HashedCredentialsMatcher
import org.apache.shiro.crypto.hash.Sha512Hash; 

beans = { 
	credentialMatcher(HashedCredentialsMatcher) {
	    storedCredentialsHexEncoded = false
	    hashIterations=1024
	    hashAlgorithmName=Sha512Hash.ALGORITHM_NAME
    } 
	
	pictureServiceJava(webpics_grails.PictureServiceJava)
}
