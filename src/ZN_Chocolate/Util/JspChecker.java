package ZN_Chocolate.Util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andrusha on 08.11.15.
 */
public class JspChecker {
    String[] importWhiteList = {
            "java.lang.reflect"
    };

    String[] methodsBlackList = {
            "request.getQueryString\n" +
                    "[Rr]equest.getParameter\n" +
                    "getProperty\\s*\\(\n" +
                    "response.sendRedirect\\s*\\(.*(Request|request).*\\)\n" +
                    "print[Ss]tack[Tt]race\n" +
                    "out\\.print(ln)?.*[Rr]equest\\.\n" +
                    "# Database rules\n" +
                    "jdbc:.*;\n" +
                    "createStatement\\s*\\(.*\\)\n" +
                    "# Network\n" +
                    "Socket\\s*\\("
    };

    public boolean checkJSP(String jsp){
        String jspHeader = StringUtils.substringBetween(jsp, "<%@", "%>");
        if (jspHeader != null) {
            Pattern pattern = Pattern.compile("import=\"([^\"]*)\"");
            Matcher m = pattern.matcher(jspHeader);

            while (m.find()) {
                System.out.println(m.group(1));
                if (m.group(1).contains("ZN_Chocolate")) {
                    continue;
                } else if (m.group(1).contains("java.lang.reflect")) {
                    continue;
                } else {
                    return true;
                }
            }
        }



        int corr = 0;
        if (jsp.contains("java.")) {
            while (true){
                corr = jsp.indexOf("java.", corr);
                if (corr==-1){break;}
                if (jsp.indexOf("java.lang.reflect",corr) != corr ){return true;}
                corr = corr + 1;

            }
        }

        if (jsp.contains("getRuntime")
                || jsp.contains("Runtime")
                || jsp.contains("exec")
                || jsp.contains("java.security.acl.acl")
                || jsp.contains("java.security")
                || jsp.contains("executeQuery")
                || jsp.contains("jdbc:")
                || jsp.contains("Socket")
                || jsp.contains("sendRedirect")
                || jsp.contains("Socket")
                || jsp.contains("java.io")
                || jsp.contains("java.nio")
                || jsp.contains("OutputStream")
                || jsp.contains("InputStream")
                || jsp.contains("File")
                || jsp.contains("ProcessBuilder")
                || jsp.contains("BufferedReader")
                || jsp.contains("getServletContext")
                || jsp.contains("getResourceAsStream")
                || jsp.contains("RequestDispatcher")
                || jsp.contains("file://")
                //Thread.currentThread().getStackTrace()
                || jsp.contains("System.")){return true;}
        return false;

    }
}
