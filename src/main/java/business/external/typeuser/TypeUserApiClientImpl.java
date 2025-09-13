package business.external.typeuser;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Stateless
public class TypeUserApiClientImpl implements TypeUserApiClient {
    private Client client = ClientBuilder.newClient();
    private static final String PATH = "http://localhost:9001/msa-typeuser/api/typeuser/";
    @Override
    public TypeUserDTO getTypeUserById(Long id) {
        return client.target(PATH + id)
                .request()
                .get(TypeUserDTO.class);
    }
}
