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

    private List<UserDto> data;

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
     * @return the data
     */
    public List<UserDto> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<UserDto> data) {
        this.data = data;
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
        String tmp1="<ROOT><data><birthday>23/04/2003</birthday><passwordExpiration>23/04/2022</passwordExpiration><gender>F</gender><failedAttempts>0</failedAttempts><link/><verified>true</verified><urlPhoto>http://localhost/recursos/imagenes/usuario/4_1239698382-img.gal11858.jpeg</urlPhoto><password>sNrcn449MAilNJHAM0OaNg==</password><name>Elsy Arias</name><id>4</id><email>elsyarias@yopmail.com</email><username>elsari</username><status>Inactive</status></data><data><birthday>28/11/1988</birthday><lastLogin>23/05/2017</lastLogin><passwordExpiration>23/04/2022</passwordExpiration><gender>F</gender><failedAttempts>0</failedAttempts><link/><verified>true</verified><urlPhoto>http://localhost/recursos/imagenes/usuario/3_20160325_143241.jpg</urlPhoto><password>zGSrMyQutqYN+u9doClXzw==</password><name>Erika Castellanos</name><id>3</id><email>castell.erika@gmail.com</email><username>ericas</username><status>Active</status></data><data><birthday>19/11/2007</birthday><lastLogin>13/06/2017</lastLogin><passwordExpiration>29/07/2017</passwordExpiration><gender>F</gender><failedAttempts>0</failedAttempts><link/><verified>true</verified><urlPhoto>http://localhost/recursos/imagenes/usuario/2_19C.jpg</urlPhoto><password>zbvufU9QU9AlIWDV6sOSiQ==</password><name>Laura Camila</name><id>2</id><email>laucastrillo@yopmail.com</email><username>laucas</username><status>Active</status></data><data><lastLogin>11/07/2017</lastLogin><gender>M</gender><failedAttempts>0</failedAttempts><link/><verified>true</verified><tokenUser/><urlPhoto>http://localhost/recursos/imagenes/usuario/1_10391534.jpg</urlPhoto><password>6ABEgsah4gtG4nBmzHfGCw==</password><name>Luis Castrillo</name><id>1</id><email>lacastrillov@gmail.com</email><username>lcastrillo</username><status>Active</status></data><success>true</success><message>Busqueda de user realizada...</message><totalCount>4</totalCount></ROOT>";
        String tmp2="<data xsi:type=\"java:com.lacv.marketplatform.dtos.RoleDto\"><priority-check>3</priority-check><name>Agente</name><description>Agente de una Empresa</description><homePage>/vista/purchaseOrder/table.htm</homePage><id xsi:type=\"java:java.lang.Integer\">4</id></data><data xsi:type=\"java:com.lacv.marketplatform.dtos.RoleDto\"><priority-check>2</priority-check><name>Empleado</name><description>Empleado de Mercando</description><homePage>/vista/inventoryOrder/table.htm</homePage><id xsi:type=\"java:java.lang.Integer\">3</id></data><data xsi:type=\"java:com.lacv.marketplatform.dtos.RoleDto\"><priority-check>4</priority-check><name>Cliente</name><description>Cliente de los servicios</description><homePage>/vista/product/table.htm</homePage><id xsi:type=\"java:java.lang.Integer\">2</id></data><data xsi:type=\"java:com.lacv.marketplatform.dtos.RoleDto\"><priority-check>1</priority-check><name>Administrador</name><description>Usuario Administrador del Sistema</description><homePage>/vista/user/table.htm</homePage><id xsi:type=\"java:java.lang.Integer\">1</id></data>";
        String tmp3="{'success':true,'message':'Busqueda de user realizada...','data':[{'id':4,'name':'Elsy Arias','email':'elsyarias@yopmail.com','username':'elsari','password':'sNrcn449MAilNJHAM0OaNg\\u003d\\u003d','gender':'F','link':'','urlPhoto':'http://localhost/recursos/imagenes/usuario/4_1239698382-img.gal11858.jpeg','birthday':'23/04/2003','status':'Inactive','verified':true,'failedAttempts':0,'passwordExpiration':'23/04/2022'},{'id':3,'name':'Erika Castellanos','email':'castell.erika@gmail.com','username':'ericas','password':'zGSrMyQutqYN+u9doClXzw\\u003d\\u003d','gender':'F','link':'','urlPhoto':'http://localhost/recursos/imagenes/usuario/3_20160325_143241.jpg','birthday':'28/11/1988','status':'Active','verified':true,'failedAttempts':0,'passwordExpiration':'23/04/2022','lastLogin':'23/05/2017'},{'id':2,'name':'Laura Camila','email':'laucastrillo@yopmail.com','username':'laucas','password':'zbvufU9QU9AlIWDV6sOSiQ\\u003d\\u003d','gender':'F','link':'','urlPhoto':'http://localhost/recursos/imagenes/usuario/2_19C.jpg','birthday':'19/11/2007','status':'Active','verified':true,'failedAttempts':0,'passwordExpiration':'29/07/2017','lastLogin':'13/06/2017'},{'id':1,'name':'Luis Castrillo','email':'lacastrillov@gmail.com','username':'lcastrillo','password':'6ABEgsah4gtG4nBmzHfGCw\\u003d\\u003d','gender':'M','link':'','urlPhoto':'http://localhost/recursos/imagenes/usuario/1_10391534.jpg','tokenUser':'','status':'Active','verified':true,'failedAttempts':0,'lastLogin':'11/07/2017'}],'totalCount':4}";
        String tmp4="[{'id':4,'name':'Elsy Arias','email':'elsyarias@yopmail.com','username':'elsari','password':'sNrcn449MAilNJHAM0OaNg\\u003d\\u003d','gender':'F','link':'','urlPhoto':'http://localhost/recursos/imagenes/usuario/4_1239698382-img.gal11858.jpeg','birthday':'23/04/2003','status':'Inactive','verified':true,'failedAttempts':0,'passwordExpiration':'23/04/2022'},{'id':3,'name':'Erika Castellanos','email':'castell.erika@gmail.com','username':'ericas','password':'zGSrMyQutqYN+u9doClXzw\\u003d\\u003d','gender':'F','link':'','urlPhoto':'http://localhost/recursos/imagenes/usuario/3_20160325_143241.jpg','birthday':'28/11/1988','status':'Active','verified':true,'failedAttempts':0,'passwordExpiration':'23/04/2022','lastLogin':'23/05/2017'},{'id':2,'name':'Laura Camila','email':'laucastrillo@yopmail.com','username':'laucas','password':'zbvufU9QU9AlIWDV6sOSiQ\\u003d\\u003d','gender':'F','link':'','urlPhoto':'http://localhost/recursos/imagenes/usuario/2_19C.jpg','birthday':'19/11/2007','status':'Active','verified':true,'failedAttempts':0,'passwordExpiration':'29/07/2017','lastLogin':'13/06/2017'},{'id':1,'name':'Luis Castrillo','email':'lacastrillov@gmail.com','username':'lcastrillo','password':'6ABEgsah4gtG4nBmzHfGCw\\u003d\\u003d','gender':'M','link':'','urlPhoto':'http://localhost/recursos/imagenes/usuario/1_10391534.jpg','tokenUser':'','status':'Active','verified':true,'failedAttempts':0,'lastLogin':'11/07/2017'}]";
        
        //Object
        System.out.println("\nOBJECT");
        ResultListUsers obj = (ResultListUsers) XMLMarshaller.convertXMLToObject(tmp1, ResultListUsers.class);
        System.out.println(Util.objectToJson(obj));
        
        String xml= XMLMarshaller.convertObjectToXML(obj);
        System.out.println(xml);
        
        //List
        System.out.println("\nLIST");
        //List<RoleDto> ciudades= XMLMarshaller.convertXMLToList(tmp2, RoleDto.class);
        //System.out.println(Util.objectToJson(ciudades));
        
        
        System.out.println("\nXML");
        /*try {
            XMLMarshaller.convertObjectToXMLFile(obj, "/home/lacastrillov/callback.xml");
            ResultListUsers obj2= (ResultListUsers) XMLMarshaller.convertXMLFileToObject("/home/lacastrillov/callback.xml", ResultListUsers.class);
            System.out.println(Util.objectToJson(obj2));
        } catch (IOException ex) {
            Logger.getLogger(ResultListUsers.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        //String xml= XMLMarshaller.convertJSONToXML(tmp4);
        //System.out.println(xml);
        
        //List<UserDto> users= XMLMarshaller.convertXMLToList(xml, UserDto.class);
        //System.out.println(Util.objectToJson(users));
    }

}
