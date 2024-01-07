<script>
    import {goto} from "$app/navigation";
    import {toastNotice} from "../../../app.js";

    $: signupData = {
        username: '',
        nickname: '',
        password1: '',
        password2: '',
        email: ''
    };

    async function signup() {
        if(passwordCheck()){
            await fetch(`http://localhost:8090/member/signup`, {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: JSON.stringify(signupData)
            });
            toastNotice("회원가입 완료.");
            await goto('/');
        }else{
            toastNotice("비밀번호가 일치하지 않습니다.");
        }
    }

    function passwordCheck() {
        if (signupData.password1 !== signupData.password2) {
            return false;
        }
        return true;
    }
    function blankCheck(){
        if (signupData.password1 == '' || signupData.password2 == '') {
            return false;
        }
        return true;
    }
</script>

<svelte:head>
    <title>Home</title>
    <meta name="description" content="Svelte demo app"/>
</svelte:head>

<section class="">
    <div class="card shadow-xl">
        <div class="card-body p-1">
            <h1 class="card-title justify-center">회원가입</h1>
            <form class="p-5">
                <div class="card-body p-1">
                    <label class="card-title" for="username">사용자ID</label>
                    <input
                        type="text"
                        class="textarea textarea-bordered card-actions justify-end"
                        placeholder="username"
                        bind:value={signupData.username}
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="nickname">닉네임</label>
                    <input
                        type="text"
                        class="textarea textarea-bordered"
                        placeholder="nickname"
                        bind:value={signupData.nickname}
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="password1">비밀번호</label>
                    <input
                        type="password"
                        class="textarea textarea-bordered"
                        placeholder="password"
                        bind:value={signupData.password1}
                    />
                </div>
                <div class="card-body p-1">
                    <!-- // 비밀번호 확인하는 과정 추가 필요 -->
                    <label class="card-title" for="password2">비밀번호 확인
                        {#if passwordCheck() && blankCheck()}
                            <span id="check">✅</span>
                        {/if}
                    </label>
                    <input
                        type="password"
                        class="textarea textarea-bordered"
                        placeholder="password confirm"
                        bind:value={signupData.password2}
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="email">이메일</label>
                    <input
                        type="email"
                        class="textarea textarea-bordered"
                        placeholder="email"
                        bind:value={signupData.email}
                    />
                </div>
                <button class="btn" type="submit" on:click={(event) => signup()}>회원가입</button>
            </form>
        </div>
    </div>
</section>

<style>
</style>
