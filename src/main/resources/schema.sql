DROP TABLE IF EXISTS tbl_gpx;

CREATE TABLE tbl_gpx (
  gpx_id INT AUTO_INCREMENT  PRIMARY KEY,
  created_time BIGINT,
  updated_time BIGINT,
  user_id INT NOT NULL
);

DROP TABLE IF EXISTS tbl_metadata;

CREATE TABLE tbl_metadata (
  metadata_id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250)  NULL,
  desc VARCHAR(2048)  NULL,
  author VARCHAR(250)  NULL,
  link VARCHAR(250)  NULL,
  metadata_text VARCHAR(500)  NULL,
  metadata_time TIMESTAMP(9)  NULL,
  gpx INT  NULL,
  FOREIGN KEY (gpx) REFERENCES tbl_gpx(gpx_id)
);

DROP TABLE IF EXISTS tbl_trkpt;

CREATE TABLE tbl_trkpt (
  trkpt_id INT AUTO_INCREMENT  PRIMARY KEY,
  lat DOUBLE  NULL,
  lon DOUBLE  NULL,
  ele DOUBLE  NULL,
  trkpt_time TIMESTAMP(9)  NULL,
  created_time BIGINT,
  gpx INT  NULL,
  FOREIGN KEY (gpx) REFERENCES tbl_gpx(gpx_id)
);

DROP TABLE IF EXISTS tbl_wpt;

CREATE TABLE tbl_wpt (
  wpt_id INT AUTO_INCREMENT  PRIMARY KEY,
  lat DOUBLE  NULL,
  lon DOUBLE  NULL,
  name VARCHAR(250)  NULL,
  sym VARCHAR(250)  NULL,
  created_time BIGINT,
  gpx INT  NULL,
  FOREIGN KEY (gpx) REFERENCES tbl_gpx(gpx_id)
);