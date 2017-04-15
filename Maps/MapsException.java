package Maps;

/**
 * General purpose Exceptions for the Maps class.
 */
class MapsException extends RuntimeException{
    MapsException(String msg) {super(msg); }

    static MapsException error(String msgFormat, Object... arguments) {
        return new MapsException(String.format(msgFormat, arguments));
    }

}

