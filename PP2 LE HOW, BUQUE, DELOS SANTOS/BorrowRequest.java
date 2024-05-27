public class BorrowRequest {
    private BorrowerInfo borrowerInfo;
    private String borrowedItems;

    public BorrowRequest(BorrowerInfo borrowerInfo, String borrowedItems) {
        this.borrowerInfo = borrowerInfo;
        this.borrowedItems = borrowedItems;
    }

    public BorrowerInfo getBorrowerInfo() {
        return borrowerInfo;
    }

    public void setBorrowerInfo(BorrowerInfo borrowerInfo) {
        this.borrowerInfo = borrowerInfo;
    }

    public String getBorrowedItems() {
        return borrowedItems;
    }

    public void setBorrowedItems(String borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    public String toString() {
        return "Borrower: " + borrowerInfo.getFirstName() + " " + borrowerInfo.getLastName() + " - " + borrowedItems;
    }
}
