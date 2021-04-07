package litecoin_parser.database;

import litecoin_parser.service.Module;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@IdClass(Error.class)
@Table(name = "LTC$ERRORS")
public class Error implements Serializable {

    private static final long serialVersionUID = 4468376402302584077L;

    @Id
    @Column(name = "MODULE_NAME", nullable = false)
    @Enumerated(EnumType.STRING)
    private Module moduleName;

    @Id
    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "WTIME", nullable = false)
    private Date wtime;
}
