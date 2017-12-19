package tadeas.data;

public class SessionKey implements SessionKeyI {

    private String token;

    public SessionKey(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }
}
