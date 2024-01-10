<script>
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";
    import {checkLogout} from "../login_check.js";

    onMount(async () => {
        await fetch(`https://gamedragons.api.bi3a.app/member/logout`, {
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