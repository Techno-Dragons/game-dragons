<script>
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";

    onMount(async () => {
        await fetch(`http://localhost:8090/member/logout`,{
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            credentials: 'include'
        });
        goto('/');
    });
</script>