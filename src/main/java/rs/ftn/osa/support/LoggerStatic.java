package rs.ftn.osa.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerStatic {

    public static void logInFile (Class cla, String msg){
         Logger logger = LoggerFactory.getLogger(cla);
         logger.info(msg);
    }

}
