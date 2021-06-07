CREATE TABLE LTC$BLOCKS (
    HASH VARCHAR(64) NOT NULL PRIMARY KEY,
    COUNT_CONFIRMATIONS INTEGER NOT NULL,
    STRIPPED_SIZE INTEGER NOT NULL,
    BLOCK_SIZE INTEGER NOT NULL,
    WEIGHT INTEGER NOT NULL,
    HEIGHT INTEGER NOT NULL,
    VERSION INTEGER NOT NULL,
    VERSION_HEX VARCHAR(8) NOT NULL,
    MERKLE_ROOT VARCHAR(64) NOT NULL,
    BLOCK_TIME INTEGER NOT NULL,
    MEDIAN_TIME INTEGER NOT NULL,
    NONCE BIGINT NOT NULL,
    BITS VARCHAR(8) NOT NULL,
    DIFFICULTY FLOAT8 NOT NULL,
    CHAIN_WORK VARCHAR(64) NOT NULL,
    TRANSACTION_COUNT INTEGER NOT NULL,
    PREVIOUS_BLOCK_HASH VARCHAR(64) NOT NULL,
    NEXT_BLOCK_HASH VARCHAR(64) NOT NULL
);

CREATE TABLE LTC$TRANSACTIONS (
    TX_ID VARCHAR(64) NOT NULL PRIMARY KEY,
    TX_HASH VARCHAR(64) NOT NULL,
    BLOCK_HASH VARCHAR(64) NOT NULL,
    TX_VERSION INTEGER NOT NULL,
    TX_SIZE INTEGER NOT NULL,
    TX_VSIZE INTEGER NOT NULL,
    WEIGHT INTEGER NOT NULL,
    LOCK_TIME BIGINT NOT NULL,
    FOREIGN KEY(BLOCK_HASH) REFERENCES LTC$BLOCKS(HASH)
);

CREATE TABLE LTC$TX_INPUTS (
    TX_ID VARCHAR(64) NOT NULL,
    TX_INPUT_ID VARCHAR(64) NOT NULL,
    VOUT INTEGER NOT NULL,
    SCRIPT_SIG_ASM VARCHAR(4000),
    SCRIPT_SIG_HEX VARCHAR(4000),
    SEQUENCE_NUMBER BIGINT NOT NULL,
    PRIMARY KEY(TX_ID, TX_INPUT_ID, VOUT),
    FOREIGN KEY(TX_ID) REFERENCES LTC$TRANSACTIONS(TX_ID)
);

CREATE TABLE LTC$TX_COINBASE_INPUTS (
    TX_ID VARCHAR(64) NOT NULL PRIMARY KEY,
    COINBASE VARCHAR(200) NOT NULL,
    SEQUENCE_NUMBER BIGINT NOT NULL,
    FOREIGN KEY(TX_ID) REFERENCES LTC$TRANSACTIONS(TX_ID)
);

CREATE TABLE LTC$TX_INPUT_WITNESS (
    ID INTEGER NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    TX_ID VARCHAR(64) NOT NULL,
    TX_INPUT_ID VARCHAR(64) NOT NULL,
    VOUT INTEGER NOT NULL,
    WITNESS_DATA VARCHAR(4000) NOT NULL,
    FOREIGN KEY(TX_ID, TX_INPUT_ID, VOUT) REFERENCES LTC$TX_INPUTS(TX_ID, TX_INPUT_ID, VOUT)
);

CREATE TABLE LTC$TX_OUTPUTS (
    TX_ID VARCHAR(64) NOT NULL,
    OUTPUT_NUMBER INTEGER NOT NULL,
    OUT_VALUE FLOAT8 NOT NULL,
    SCRIPT_ASM VARCHAR(4000),
    SCRIPT_HEX VARCHAR(4000),
    SCRIPT_REQ_SIGNATURES INTEGER,
    SCRIPT_TYPE VARCHAR(40) NOT NULL,
    PRIMARY KEY(TX_ID, OUTPUT_NUMBER),
    FOREIGN KEY(TX_ID) REFERENCES LTC$TRANSACTIONS(TX_ID)
);

CREATE TABLE LTC$ADDRESSES (
    WALLET_ADDRESS VARCHAR(120) NOT NULL PRIMARY KEY
);

CREATE TABLE LTC$TX_OUT_ADDRESSES (
    WALLET_ADDRESS VARCHAR(120) NOT NULL,
    TX_ID VARCHAR(64) NOT NULL,
    OUTPUT_NUMBER INTEGER NOT NULL,
    PRIMARY KEY(WALLET_ADDRESS, TX_ID, OUTPUT_NUMBER),
    FOREIGN KEY(WALLET_ADDRESS) REFERENCES LTC$ADDRESSES(WALLET_ADDRESS),
    FOREIGN KEY(TX_ID, OUTPUT_NUMBER) REFERENCES LTC$TX_OUTPUTS(TX_ID, OUTPUT_NUMBER)
);

CREATE TABLE LTC$ERRORS (
    MODULE_NAME VARCHAR(60) NOT NULL,
    MESSAGE VARCHAR(4000) NOT NULL,
    WTIME TIMESTAMP NOT NULL,
    PRIMARY KEY(MODULE_NAME, MESSAGE, WTIME)
)

ALTER TABLE LTC$TX_OUTPUTS ADD COLUMN IS_SPENT BOOLEAN;
ALTER TABLE LTC$TX_INPUTS ADD COLUMN IN_VALUE FLOAT8;

SELECT * FROM LTC$BLOCKS;
SELECT * FROM LTC$TRANSACTIONS;
SELECT * FROM LTC$TX_INPUTS;
SELECT * FROM LTC$TX_COINBASE_INPUTS;
SELECT * FROM LTC$TX_INPUT_WITNESS;
SELECt * FROM LTC$TX_OUTPUTS;
SELECT * FROM LTC$ADDRESSES;
SELECT * FROM LTC$TX_OUT_ADDRESSES;
SELECT * FROM LTC$ERRORS;

COMMIT;

DROP TABLE LTC$BLOCKS;
DROP TABLE LTC$TRANSACTIONS;
DROP TABLE LTC$TX_INPUTS;
DROP TABLE LTC$TX_COINBASE_INPUTS;
DROP TABLE LTC$TX_INPUT_WITNESS;
DROP TABLE LTC$TX_OUTPUTS;
DROP TABLE LTC$ADDRESSES;
DROP TABLE LTC$TX_OUT_ADDRESSES;
DROP TABLE LTC$ERRORS;
