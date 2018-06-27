package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import project.Priority;
import project.Status;

/**
 * Class similar to Task but only relies on String parameters Used for
 * creating/representation of tasks in tblTasks
 * 
 * @author Julia Hofer
 */
public class TableTask {
	private String tname;
	private String tremark;
	private String tstatus;
	private String tpriority;
	private String tdate;

	/**
	 * constructor
	 * 
	 * @author Julia Hofer
	 * @param tname
	 *            the name of the task
	 * @param tremark
	 *            the remark of the task
	 * @param tstatus
	 *            the status of the task
	 * @param tpriority
	 *            the priority of the task
	 * @param tdate
	 *            the date of the task
	 */
	public TableTask(String tname, String tremark, String tstatus, String tpiority, String tdate) {
		this.tname = tname;
		this.tremark = tremark;
		this.tstatus = tstatus;
		this.tpriority = tpiority;
		this.tdate = tdate;
	}

	/**
	 * constructor with regarding types
	 * 
	 * @author Lukas Schiefermueller
	 * @param tname
	 *            the name of the task
	 * @param tremark
	 *            the remark of the task
	 * @param tstatus
	 *            the status of the task
	 * @param tpriority
	 *            the priority of the task
	 * @param tdate
	 *            the date of the task
	 */
	public TableTask(String tname, String tremark, Status tstatus, Priority tpriority, Date tdate) {
		this(tname, tremark, "", "", "");
		String dateString = "";
		Date date;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		if ((date = tdate) != null) {
			dateString = dateFormatter.format(date);
		}
		String priorityString = "";
		Priority priority;
		if ((priority = tpriority) != null)
			priorityString = priority.toString();
		String statusString = "";
		Status status;
		if ((status = tstatus) != null)
			statusString = status.getStatus();
		this.tstatus = statusString;
		this.tpriority = priorityString;
		this.tdate = dateString;
	}

	/**
	 * getter for tname
	 * 
	 * @return the name of the task
	 */
	public String getTname() {
		return tname;
	}

	/**
	 * getter for tremark
	 * 
	 * @return the remark of the task
	 */
	public String getTremark() {
		return tremark;
	}

	/**
	 * getter for tstatus
	 * 
	 * @return the status of the task
	 */
	public String getTstatus() {
		return tstatus;
	}

	/**
	 * getter for tpriority
	 * 
	 * @return the priority of the task
	 */
	public String getTpriority() {
		return tpriority;
	}

	/**
	 * getter for tdate
	 * 
	 * @return the date of the task
	 */
	public String getTdate() {
		return tdate;
	}
}