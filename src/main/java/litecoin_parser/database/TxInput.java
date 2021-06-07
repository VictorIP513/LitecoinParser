package litecoin_parser.database;

import litecoin_parser.database.id.TxInputId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@IdClass(TxInputId.class)
@Table(name = "LTC$TX_INPUTS")
public class TxInput extends TxInputId {

    private static final long serialVersionUID = -8649663015253835073L;

    @Column(name = "SCRIPT_SIG_ASM")
    private String scriptSigAsm;

    @Column(name = "SCRIPT_SIG_HEX")
    private String scriptSigHex;

    @Column(name = "SEQUENCE_NUMBER", nullable = false)
    private Long sequenceNumber;

    @Column(name = "OUT_VALUE")
    private Double inValue;
}
