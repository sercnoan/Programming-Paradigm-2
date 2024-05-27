import java.util.List;

public class FacultyApproval {
    private List<BorrowRequest> borrowRequests;

    public List<BorrowRequest> getBorrowRequests() {
        return borrowRequests;
    }

    public void setBorrowRequests(List<BorrowRequest> borrowRequests) {
        this.borrowRequests = borrowRequests;
    }

    public void approveRequest(BorrowRequest request) {
        if (borrowRequests != null && borrowRequests.contains(request)) {
            borrowRequests.remove(request);
        }
    }

    public void denyRequest(BorrowRequest request) {
        if (borrowRequests != null && borrowRequests.contains(request)) {
            borrowRequests.remove(request);
        }
    }
}