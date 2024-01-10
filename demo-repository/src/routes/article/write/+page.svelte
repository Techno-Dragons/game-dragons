<script>
    import axios from 'axios';
    import {onMount} from 'svelte';

    let inputTitle = $state();
    let inputContent = $state();
    let imageFile;

    function handleFileChange(event) {
        imageFile = event.target.files[0];
    }

    async function postArticle() {
        const formData = new FormData();

        const articleData = JSON.stringify({
            title: inputTitle,
            content: inputContent
        });

        formData.append('articleRequestDto', new Blob([articleData], {type: 'application/json'}));
        formData.append('imageFile', imageFile);

        const res = await axios.post(`https://gamedragons.api.bi3a.app/article`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            },
            withCredentials: true
        });

        console.log(res);
        console.log(inputTitle);
        console.log(inputContent);
        window.location.href = `/article/${res.data.data}`;
    }

    async function login() {
        try {
            let res = await axios.post(
                `https://gamedragons.api.bi3a.app/member/login`,
                {
                    username: '123',
                    password: '123'
                },
                {
                    withCredentials: true
                }
            );
            console.log(res);
            return res;
        } catch (error) {
            console.log('loginError');
        }
    }

    onMount(async () => {

    })

</script>

<div class="width-45 mr-auto ml-auto">
	<textarea
            class="textarea textarea-ghost text-4xl w-full"
            placeholder="제목을 입력해주세요."
            bind:value={inputTitle}
    />
    <div class="flex items-center space-x-2 mt-6"/>
    <div class="divider divider-Neutral mb-1"/>

    <input
            type="file"
            class="file-input file-input-bordered file-input-primary w-full max-w-xs"
            on:change={handleFileChange}
    />

    <div class="mt-8">
		<textarea
                class="textarea textarea-ghost mt-1 w-full h-screen"
                placeholder="내용을 입력해주세요."
                bind:value={inputContent}
        />
    </div>

    <div class="flex space-x-4 mb-6 container flex-wrap flex-row-reverse">
        <div class="flex-wrap">
            <button
                    class="btn border-gray-600 btn-ghost"
                    on:click={() => {
					postArticle();
					inputTitle = '';
					inputContent = '';
				}}
            >
                작성 완료
            </button>
            <button
                    class="btn border-gray-600 btn-ghost"
                    on:click={() => {
					inputTitle = '';
					inputContent = '';
					location.href = "./"
				}}
            >
                취소
            </button>
        </div>
    </div>
</div>

<style>
    .flex-container {
        display: flex;
        align-items: center;
    }

    .width-45 {
        width: 45%;
    }
</style>
