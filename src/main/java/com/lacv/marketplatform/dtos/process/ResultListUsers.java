/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.util.Util;
import com.dot.gcpbasedot.util.XMLMarshaller;
import com.lacv.marketplatform.dtos.UserDto;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lacastrillov
 */
@XmlRootElement
public class ResultListUsers {

    private boolean success;

    private String message;

    private List<UserDto> users;

    private Long totalCount;

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the users
     */
    public List<UserDto> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    /**
     * @return the totalCount
     */
    public Long getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
    
    public static void main(String[] args) {
        String tmp1="<ResultListCallback><users><birthday>23/04/2003</birthday><passwordExpiration>23/04/2022</passwordExpiration><gender>F</gender><failedAttempts>0</failedAttempts><link/><verified>true</verified><urlPhoto>http://localhost/recursos/imagenes/usuario/4_1239698382-img.gal11858.jpeg</urlPhoto><password>sNrcn449MAilNJHAM0OaNg==</password><name>Elsy Arias</name><id>4</id><email>elsyarias@yopmail.com</email><username>elsari</username><status>Inactive</status></users><users><birthday>28/11/1988</birthday><lastLogin>23/05/2017</lastLogin><passwordExpiration>23/04/2022</passwordExpiration><gender>F</gender><failedAttempts>0</failedAttempts><link/><verified>true</verified><urlPhoto>http://localhost/recursos/imagenes/usuario/3_20160325_143241.jpg</urlPhoto><password>zGSrMyQutqYN+u9doClXzw==</password><name>Erika Castellanos</name><id>3</id><email>castell.erika@gmail.com</email><username>ericas</username><status>Active</status></users><users><birthday>19/11/2007</birthday><lastLogin>13/06/2017</lastLogin><passwordExpiration>29/07/2017</passwordExpiration><gender>F</gender><failedAttempts>0</failedAttempts><link/><verified>true</verified><urlPhoto>http://localhost/recursos/imagenes/usuario/2_19C.jpg</urlPhoto><password>zbvufU9QU9AlIWDV6sOSiQ==</password><name>Laura Camila</name><id>2</id><email>laucastrillo@yopmail.com</email><username>laucas</username><status>Active</status></users><users><lastLogin>11/07/2017</lastLogin><gender>M</gender><failedAttempts>0</failedAttempts><link/><verified>true</verified><tokenUser/><urlPhoto>http://localhost/recursos/imagenes/usuario/1_10391534.jpg</urlPhoto><password>6ABEgsah4gtG4nBmzHfGCw==</password><name>Luis Castrillo</name><id>1</id><email>lacastrillov@gmail.com</email><username>lcastrillo</username><status>Active</status></users><success>true</success><message>Busqueda de user realizada...</message><totalCount>4</totalCount></ResultListCallback>";
        String tmp2="<UserDto><birthday>23/04/2003</birthday><status>Inactive</status><link/><urlPhoto>http://localhost/recursos/imagenes/usuario/4_1239698382-img.gal11858.jpeg</urlPhoto><passwordExpiration>23/04/2022</passwordExpiration><password>sNrcn449MAilNJHAM0OaNg==</password><id>4</id><username>elsari</username><email>elsyarias@yopmail.com</email><failedAttempts>0</failedAttempts><verified>true</verified><name>Elsy Arias</name><gender>F</gender></UserDto><UserDto><birthday>28/11/1988</birthday><status>Active</status><link/><urlPhoto>http://localhost/recursos/imagenes/usuario/3_20160325_143241.jpg</urlPhoto><passwordExpiration>23/04/2022</passwordExpiration><password>zGSrMyQutqYN+u9doClXzw==</password><id>3</id><lastLogin>23/05/2017</lastLogin><username>ericas</username><email>castell.erika@gmail.com</email><failedAttempts>0</failedAttempts><verified>true</verified><name>Erika Castellanos</name><gender>F</gender></UserDto><UserDto><birthday>19/11/2007</birthday><status>Active</status><link/><urlPhoto>http://localhost/recursos/imagenes/usuario/2_19C.jpg</urlPhoto><passwordExpiration>29/07/2017</passwordExpiration><password>zbvufU9QU9AlIWDV6sOSiQ==</password><id>2</id><lastLogin>13/06/2017</lastLogin><username>laucas</username><email>laucastrillo@yopmail.com</email><failedAttempts>0</failedAttempts><verified>true</verified><name>Laura Camila</name><gender>F</gender></UserDto><UserDto><tokenUser/><status>Active</status><link/><urlPhoto>http://localhost/recursos/imagenes/usuario/1_10391534.jpg</urlPhoto><password>6ABEgsah4gtG4nBmzHfGCw==</password><id>1</id><lastLogin>11/07/2017</lastLogin><username>lcastrillo</username><email>lacastrillov@gmail.com</email><failedAttempts>0</failedAttempts><verified>true</verified><name>Luis Castrillo</name><gender>M</gender></UserDto>";
        
        System.out.println("\n XML to Object");
        ResultListUsers obj = (ResultListUsers) XMLMarshaller.convertXMLToObject(tmp1, ResultListUsers.class);
        System.out.println(Util.objectToJson(obj));
        
        System.out.println("\n XML to LIST");
        List<UserDto> users= XMLMarshaller.convertXMLToList(tmp2, UserDto.class);
        System.out.println(Util.objectToJson(users));
        
        System.out.println("\n Object to XML");
        String xml= XMLMarshaller.convertObjectToXML(obj);
        System.out.println(xml);
        
        System.out.println("\n LIST to XML");
        String xml2= XMLMarshaller.convertObjectToXML(users);
        System.out.println(xml2);
        
    }

}
