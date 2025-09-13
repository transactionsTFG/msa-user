package business.external.typeuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeUserDTO {
    private Long id;
    private Boolean isActive;
    private String name;
}
