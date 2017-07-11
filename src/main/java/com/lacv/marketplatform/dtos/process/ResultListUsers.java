/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacv.marketplatform.dtos.process;

import com.dot.gcpbasedot.util.Util;
import com.dot.gcpbasedot.util.XMLMarshaller;
import com.lacv.marketplatform.dtos.RoleDto;
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
        String tmp1="<result-list-callback success=\"true\"><total-count>3</total-count><message>Busqueda de user realizada...</message><data xsi:type=\"java:com.lacv.marketplatform.dtos.UserDto\"><name>Laura Camila</name><id xsi:type=\"java:java.lang.Integer\">3</id><passwordExpiration>2020-02-20T00:00:00.000-05:00</passwordExpiration><verified>true</verified><status>Active</status><username>laucas</username><failedAttempts>0</failedAttempts><email>laucas@gmail.com</email><lastLogin>2017-06-05T00:00:00.000-05:00</lastLogin><link/><gender>F</gender><password>6GwPKJnT0YQLBAQ/7ibUdA==</password><birthday>2008-05-02T00:00:00.000-05:00</birthday></data><data xsi:type=\"java:com.lacv.marketplatform.dtos.UserDto\"><name>Erika Castellanos</name><id xsi:type=\"java:java.lang.Integer\">2</id><passwordExpiration>2020-09-22T00:00:00.000-05:00</passwordExpiration><verified>true</verified><status>Active</status><username>ericas</username><failedAttempts>0</failedAttempts><email>castell.erika@gmail.com</email><lastLogin>2017-04-24T09:20:01.000-05:00</lastLogin><link>http://org.ericas.es/miperfil</link><gender>F</gender><password>zGSrMyQutqYN+u9doClXzw==</password><birthday>1993-08-17T00:00:00.000-05:00</birthday></data><data xsi:type=\"java:com.lacv.marketplatform.dtos.UserDto\"><name>Luis Castrillo</name><id xsi:type=\"java:java.lang.Integer\">1</id><passwordExpiration>2020-04-20T00:00:00.000-05:00</passwordExpiration><verified>true</verified><status>Active</status><username>lcastrillo</username><failedAttempts>0</failedAttempts><token-user/><email>lcastrillo@grupodot.com</email><lastLogin>2017-07-11T11:50:47.000-05:00</lastLogin><link>http://www.lacvsis.co/services/rest/api</link><gender>M</gender><url-photo>http://localhost/recursos/imagenes/usuario/1_image_luis_hv.jpg</url-photo><password>Z/7dAYhc3z2/0MZ8WjoUDQ==</password><birthday>1986-09-25T00:00:00.000-05:00</birthday></data></result-list-callback>";
        String tmp2="<data xsi:type=\"java:com.lacv.marketplatform.dtos.RoleDto\"><priority-check>3</priority-check><name>Agente</name><description>Agente de una Empresa</description><homePage>/vista/purchaseOrder/table.htm</homePage><id xsi:type=\"java:java.lang.Integer\">4</id></data><data xsi:type=\"java:com.lacv.marketplatform.dtos.RoleDto\"><priority-check>2</priority-check><name>Empleado</name><description>Empleado de Mercando</description><homePage>/vista/inventoryOrder/table.htm</homePage><id xsi:type=\"java:java.lang.Integer\">3</id></data><data xsi:type=\"java:com.lacv.marketplatform.dtos.RoleDto\"><priority-check>4</priority-check><name>Cliente</name><description>Cliente de los servicios</description><homePage>/vista/product/table.htm</homePage><id xsi:type=\"java:java.lang.Integer\">2</id></data><data xsi:type=\"java:com.lacv.marketplatform.dtos.RoleDto\"><priority-check>1</priority-check><name>Administrador</name><description>Usuario Administrador del Sistema</description><homePage>/vista/user/table.htm</homePage><id xsi:type=\"java:java.lang.Integer\">1</id></data>";
        
        XMLMarshaller xm= new XMLMarshaller();
        
        //Object
        ResultListUsers obj = (ResultListUsers) xm.convertXMLToObject(tmp1, ResultListUsers.class);
        System.out.println(Util.objectToJson(obj));
        
        //List
        List<RoleDto> ciudades= xm.convertXMLToList(tmp2, RoleDto.class);
        System.out.println(Util.objectToJson(ciudades));
        
    }

}
