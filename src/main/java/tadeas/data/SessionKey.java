package tadeas.data;

import java.io.Serializable;

public class SessionKey implements SessionKeyI, Serializable {

    private static final long serialVersionUID = 6623362349396629669L;

    private String token;

    public SessionKey(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionKey that = (SessionKey) o;

        return token != null ? token.equals(that.token) : that.token == null;
    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SessionKey{" +
                "token='" + token.substring(0, 5) + "...'" +
                '}';
    }
}
