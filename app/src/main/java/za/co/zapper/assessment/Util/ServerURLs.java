package za.co.zapper.assessment.Util;

/**
 * Created by SaurabhB on 2017/02/23.
 */
public enum ServerURLs {
    MASTER_URL("http://demo4012764.mockable.io/person");

    private final String url;

    /**
     *
     * @param url
     */
    private ServerURLs(final String url) {
        this.url = url;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return url;
    }

}
