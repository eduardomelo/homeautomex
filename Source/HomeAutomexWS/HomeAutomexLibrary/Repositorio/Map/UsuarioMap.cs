using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class UsuarioMap : EntityTypeConfiguration<Usuario>
    {
        public UsuarioMap()
        {
            ToTable("USUARIO");

      
            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_USUARIO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Nome).HasColumnName("DS_NOME").IsRequired();
            Property(e => e.Login).HasColumnName("DS_USUARIO");
            Property(e => e.Telefone).HasColumnName("NM_TELEFONE");
            Property(e => e.Celular).HasColumnName("NM_CELULAR");
            Property(e => e.Email).HasColumnName("DS_EMAIL");
            Property(e => e.Senha).HasColumnName("DS_SENHA");
        }

    }

}
