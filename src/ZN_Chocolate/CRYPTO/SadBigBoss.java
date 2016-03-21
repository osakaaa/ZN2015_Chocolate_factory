
package ZN_Chocolate.CRYPTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.Random;


/**
 * <p>Java class for secretGrandParent complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="secretGrandParent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "secretGrandParent")
@XmlSeeAlso({
        SecretParent11 .class
})
public class SadBigBoss {
    private String TrueSecretChocolateKey(){

        return "ZNV:key_v2_d1356d1fbfaf64d21a606b37c4e8dc5d";
    }

    public String GrandParentKey(){

        return "gR@Nd_p@r3n7_k3y_a0a81ab87f74d307b8e51fd85048e714";
    }

    protected String getRandomHexString(int seed){
        Random r = new Random(seed);
        int numchars = 32;
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }

}
