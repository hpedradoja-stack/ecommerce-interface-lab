/**
 * Global fetch wrapper for handling authentication errors
 */
async function apiFetch(url, options = {}) {
    try {
        const response = await fetch(url, {
            ...options,
            credentials: "include" // IMPORTANT for session cookies
        });

        // ======================
        // HANDLE 401 UNAUTHORIZED
        // ======================
        if (response.status === 401) {
            alert("Session expired. Please login again.");
            window.location.href = "login.html";
            return;
        }

        // ======================
        // HANDLE 403 FORBIDDEN
        // ======================
        if (response.status === 403) {
            alert("Access Denied: You do not have permission.");
            showAccessDenied();
            return;
        }

        return response;

    } catch (error) {
        console.error("API Error:", error);
    }
}
/**
 * Show access denied message on page
 */
function showAccessDenied() {
    const container = document.querySelector("main") || document.body;
    container.innerHTML = `
        <h2 style="color:red;">Access Denied</h2>
        <p>You do not have permission to view this page.</p>
    `;
}
