CREATE TABLE E_USER(
	USER_ID				INTEGER	    NOT NULL PRIMARY KEY AUTO_INCREMENT,
	CREATE_DATE			TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	USER_LOGIN_TYPE		SMALLINT	NOT NULL,
	ACTIVE				CHAR(1) 	NOT NULL CHECK (ACTIVE IN ('Y','N')),
	CONSTRAINT FK_USER_USER_LOGIN_TYPE FOREIGN KEY (USER_LOGIN_TYPE) REFERENCES R_USER_LOGIN_TYPE(CODE)
);

CREATE TABLE E_USER_FACEBOOK(
	FACEBOOK_ID			VARCHAR(100)NOT NULL,
	USER_ID				INTEGER	    NOT NULL,
	TOKEN				VARCHAR(250)NOT NULL,
	EXPIRE_DATE			DATE		NOT NULL,
	MODIFY_DATE			TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT PK_USER_FACEBOOK			PRIMARY KEY (FACEBOOK_ID, USER_ID),
	CONSTRAINT FK_USER_FACEBOOK_USER_ID FOREIGN KEY (USER_ID) REFERENCES E_USER(USER_ID)
);

CREATE TABLE ER_USER_ROLE(
	USER_ID				INTEGER		NOT NULL,
	ROLE_CODE			SMALLINT	NOT NULL,
	CONSTRAINT PK_ER_USER_ROLE				PRIMARY KEY (USER_ID, ROLE_CODE),
	CONSTRAINT FK_ER_USER_ROLE_USER_ID		FOREIGN KEY (USER_ID) REFERENCES E_USER(USER_ID),
	CONSTRAINT FK_ER_USER_ROLE_ROLE_CODE	FOREIGN KEY (ROLE_CODE) REFERENCES R_ROLE(CODE)
);

