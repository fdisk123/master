package project.master.dbmaamger;

public interface HibernateReplicationMode {
	final String EXCEPTION = "EXCEPTION";
	final String IGNORE = "IGNORE";
	final String OVERWRITE = "OVERWRITE";
	final String LATEST_VERSION = "LATEST_VERSION";
}
