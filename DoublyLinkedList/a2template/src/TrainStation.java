package a2template.src;

public class TrainStation {
	
	private TrainStation left;
	private TrainStation right;
	private boolean rightTerminal;
	private boolean leftTerminal;
	private String name;
	private TrainLine line;
	public boolean hasConnection;
	private TrainStation transfersToStation;
	private TrainLine transfersToLine;

	public TrainStation(TrainStation left, TrainStation right, boolean lTerminal, boolean rTerminal, String name,
			TrainLine line, boolean isTransfer, TrainStation transfersToStation, TrainLine transfersToLine) {
		this.left = left;
		this.right = right;
		this.leftTerminal = lTerminal;
		this.rightTerminal = rTerminal;
		this.name = name;
		this.line = line;
		this.hasConnection = isTransfer;
		this.transfersToStation = transfersToStation;
		this.transfersToLine = transfersToLine;
	}

	public TrainStation(String name) {
		this.left = null;
		this.right = null;
		this.leftTerminal = false;
		this.rightTerminal = false;
		this.name = name;
		this.line = null;
		this.hasConnection = false;
		this.transfersToStation = null;
		this.transfersToLine = null;
	}

	public TrainStation getLeft() {
		return this.left;
	}

	public TrainStation getRight() {
		return this.right;
	}

	public boolean isRightTerminal() {
		return this.rightTerminal;
	}

	public boolean isLeftTerminal() {
		return this.leftTerminal;
	}

	public String getName() {
		return this.name;
	}

	public TrainLine getLine() {
		return this.line;
	}

	public TrainStation getTransferStation() {
		return this.transfersToStation;
	}

	public TrainLine getTransferLine() {
		return this.transfersToLine;
	}

	public void setLeft(TrainStation left) {
		this.left = left;
	}

	public void setRight(TrainStation right) {
		this.right = right;
	}

	public void setLeftTerminal() {
		this.leftTerminal = true;
	}

	public void setRightTerminal() {
		this.rightTerminal = true;
	}

	public void setNonTerminal() {
		this.rightTerminal = false;
		this.leftTerminal = false;
	}

	public void setConnection(TrainLine conL, TrainStation conS) {
		this.transfersToStation = conS;
		this.transfersToLine = conL;
		this.hasConnection = true;
	}

	public void setTrainLine(TrainLine l) {
		this.line = l;
	}

	public boolean equals(TrainStation station1) {
		try {

			// checking if both are left terminal
			if (this.leftTerminal != station1.isLeftTerminal()) {
				return false;
			}
			// checking if both are right terminal
			if (this.rightTerminal != station1.isRightTerminal()) {
				return false;
			}
			// checking if both have the same name
			if (!this.name.equals(station1.getName())) {
				return false;
			}

			if (!this.leftTerminal) {
				// if not left terminal check if both are null
				if (this.left == null) {
					if (station1.left != null)
						return false;
				}
				// check if both the nodes points to nodes having the same name
				else {
					if (!this.left.getName().equals(station1.getLeft().getName())) {
						return false;
					}
				}
			}

			if (!this.rightTerminal) {
				// if not right terminal check if both are null
				if (this.right == null) {
					if (station1.right != null)
						return false;
				}
				// check if both the nodes point to nodes having the same name
				else {
					if (!this.right.getName().equals(station1.getRight().getName())) {
						return false;
					}
				}
			}

			// check the line
			if (this.line == null) {
				if (!(station1.getLine() == null))
					return false;
			} else {
				if (!this.line.getName().equals(station1.line.getName())) {
					return false;
				}
			}

			// check for hasConnection
			if (this.hasConnection) {

				if (this.hasConnection == station1.hasConnection) {
					// if both has connection check if the transfer to stations with the same name
					if (!this.transfersToStation.getName().equals((station1.transfersToStation.getName())))
						return false;
				} else
					return false;

				// and they transfer to the same line
				if (!this.transfersToLine.getName().equals(station1.transfersToLine.getName())) {
					return false;
				}
			} else if (!(this.hasConnection == station1.hasConnection))
				return false;

			return true;
		} catch (Exception e) {
			return false;
		}
	}

}