<script>
    import {onMount} from "svelte";

    $: userData = {
        username: '',
        nickname: '',
        password1: '',
        password2: '',
        email: ''
    };

    onMount(async () => {
        let response = await fetch(`http://localhost:8090/member/mypage`);
        response = await response.json();
    });

    function passwordCheck() {
        if (userData.password1 == userData.password2) {
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
            <h1 class="card-title justify-center">마이페이지</h1>
            <form class="p-5">
                <div class="card-body p-1">
                    <label class="card-title" for="username">사용자ID</label>
                    <input
                            type="text"
                            class="textarea textarea-bordered card-actions justify-end"
                            placeholder="{userData.username}"
                            bind:value={userData.username}
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="nickname">닉네임</label>
                    <input
                            type="text"
                            class="textarea textarea-bordered"
                            placeholder="{userData.username}"
                            bind:value={userData.nickname}
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="password1">비밀번호</label>
                    <input
                            type="password"
                            class="textarea textarea-bordered"
                            placeholder="{userData.username}"
                            bind:value={userData.password1}
                    />
                </div>
                <div class="card-body p-1">
                    <!-- // 비밀번호 확인하는 과정 추가 필요 -->
                    <label class="card-title" for="password2">비밀번호 확인
                        {#if passwordCheck()}
                            <span id="check">✅</span>
                        {/if}
                    </label>
                    <input
                            type="password"
                            class="textarea textarea-bordered"
                            placeholder="{userData.username} confirm"
                            bind:value={userData.password2}
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="email">이메일</label>
                    <input
                            type="email"
                            class="textarea textarea-bordered"
                            placeholder="{userData.username}"
                            bind:value={userData.email}
                    />
                </div>
                <button class="btn" type="submit">변경사항 저장</button>
            </form>
        </div>
    </div>

</section>

<style>

</style>
