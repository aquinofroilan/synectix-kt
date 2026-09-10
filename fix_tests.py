import re

files = [
    "src/test/kotlin/com/aquinofroilan/tessera/domain/assets/controller/AssetRevaluationControllerTest.kt",
    "src/test/kotlin/com/aquinofroilan/tessera/domain/assets/controller/AssetTransferControllerTest.kt"
]

mock_beans = """
    @MockitoBean
    private lateinit var organizationStatusInterceptor: OrganizationStatusInterceptor

    @MockitoBean
    private lateinit var userRepository: UserRepository

    @MockitoBean
    private lateinit var sessionTokenRepository: SessionTokenRepository

    @MockitoBean
    private lateinit var refreshTokenRepository: RefreshTokenRepository

    @MockitoBean
    private lateinit var passwordResetTokenRepository: PasswordResetTokenRepository

    @MockitoBean
    private lateinit var invitationRepository: com.aquinofroilan.tessera.domain.auth.repository.InvitationRepository

    @MockitoBean
    private lateinit var organizationRepository: com.aquinofroilan.tessera.domain.organization.repository.OrganizationRepository

    @MockitoBean
    private lateinit var authService: com.aquinofroilan.tessera.domain.auth.service.AuthService

    @MockitoBean
    private lateinit var apiKeyService: com.aquinofroilan.tessera.domain.auth.service.ApiKeyService

    @MockitoBean
    private lateinit var rolePermissionCache: com.aquinofroilan.tessera.security.RolePermissionCache

    @MockitoBean
    private lateinit var tokenHasher: com.aquinofroilan.tessera.util.TokenHasher

    @MockitoBean
    private lateinit var accountService: com.aquinofroilan.tessera.domain.finance.service.AccountService

    @MockitoBean
    private lateinit var journalEntryService: com.aquinofroilan.tessera.domain.finance.service.JournalEntryService

    @MockitoBean
    private lateinit var authenticationContext: AuthenticationContext
"""

for file in files:
    with open(file, "r") as f:
        content = f.read()

    # Find the block of @MockitoBeans (excluding the main service)
    start_pattern = r"    @MockitoBean\n    private lateinit var organizationStatusInterceptor.*"
    end_pattern = r"    private val testOrgId"
    
    parts = re.split(start_pattern, content)
    head = parts[0]
    tail = re.split(end_pattern, parts[1])[1]
    
    new_content = head + mock_beans + "\n    private val testOrgId" + tail
    
    with open(file, "w") as f:
        f.write(new_content)

