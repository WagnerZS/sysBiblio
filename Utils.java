public final class Utils {
    
    public static Boolean nullOrEmpty(Object object){
        if (object instanceof String){
            String str = (String) object;
            return str == null || str.isEmpty();
        }

        return false;
    }

}
