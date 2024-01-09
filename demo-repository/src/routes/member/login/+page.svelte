<script>
    import {goto} from "$app/navigation";
    import {checkLogin} from "../login_check.js";
    import {isLogin} from "$lib/login_stores.js";
    import {toastNotice} from "../../../app.js";

    checkLogin("로그인 상태 입니다.");
    $: username = '';
    $: password = '';
    async function login() {
        await fetch(`https://gamedragons.api.bi3a.app/member/login`, {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            credentials: 'include', // 로컬에 쿠키를 저장하기 위한 옵션..
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
        .then((res) => res.json())
        .then((res) => {
            console.log(res.data);
            if(check_login(res)){
                localStorage.setItem('username', res.data.username);
                localStorage.setItem('nickname', res.data.nickname);
            }
        });
        isLogin.set(true);
        await goto('/');
    }

    function check_login(res) {
        toastNotice(res.msg);
        if (res.data.username) {
            return true;
        }
        return false;
    }
</script>

<svelte:head>
    <title>Home</title>
    <meta name="description" content="Svelte demo app"/>
</svelte:head>

<section>
    <div class="card shadow-xl">
        <div class="card-body p-1">
            <h1 class="card-title justify-center">유저 로그인</h1>
            <form>
                <div class="card-body p-1">
                    <label class="card-title" for="username">사용자ID</label>
                    <input
                        class="textarea textarea-bordered"
                        placeholder="username"
                        type="text"
                        id="username"
                        bind:value={username}
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="password">비밀번호</label>
                    <input
                        class="textarea textarea-bordered"
                        placeholder="password"
                        type="password"
                        id="password"
                        bind:value={password}
                    />
                </div>
                <button class="btn" type="submit" on:click={(event) => login()}>로그인</button>
            </form>
        </div>
    </div>
</section>

<style>
</style>
