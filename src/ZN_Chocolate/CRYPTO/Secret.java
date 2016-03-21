
package ZN_Chocolate.CRYPTO;

import ZN_Chocolate.ZNExceptions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for secret complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="secret">
 *   &lt;complexContent>
 *     &lt;extension base="{}secretParent1">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "secret")
public class Secret
        extends SecretParent1
{
    public String generateSystemKey(){

        return "THE_KEY_IS_f75a48822b80beae3e24fb9176af3b72";
    }
    public String generateUserkey(String seed) throws ZNExceptions {
        int iseed;
        try {
            iseed = Integer.parseInt(seed);
        } catch (Exception e){
            throw new ZNExceptions("Dangerous Error Generating Secret key. Shutdown JVM immediately!");
        }
        String  key = getRandomHexString(iseed);
        return key;

    }



}
