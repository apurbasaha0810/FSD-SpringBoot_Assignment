DROP TABLE IF EXISTS USER;

CREATE TABLE USER
(
 USER_ID INTEGER AUTO_INCREMENT PRIMARY KEY,
 USER_NAME VARCHAR(100) NOT NULL,
 EMAIL VARCHAR(200) NOT NULL,
 PASSWORD VARCHAR(100) NOT NULL

)