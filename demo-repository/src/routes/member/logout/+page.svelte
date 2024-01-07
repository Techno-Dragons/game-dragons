<script>
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";
    import {checkLogout} from "../login_check.js";

    onMount(async () => {
        await fetch(`http://localhost:8090/member/logout`,{
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            credentials: 'include'
        });
        localStorage.removeItem("username");
        localStorage.removeItem("nickname");
        goto('/');
    });
</script>